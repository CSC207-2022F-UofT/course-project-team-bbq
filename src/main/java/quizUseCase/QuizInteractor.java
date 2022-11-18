package quizUseCase;

import dataAccess.DBGateway;
import entities.*;
import entityRequestModels.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Application Business Rules
 */
public class QuizInteractor implements QuizInputBoundary {
    // entities
    private QuizSettings quizSettings;
    private Quiz quiz;

    // output boundary
    private final QuizOutputBoundary presenter;

    // data access
    private final DBGateway gateway;

    // bob the builder
    private final QuizBuilder quizBuilder;

    public QuizInteractor(DBGateway gateway, QuizOutputBoundary presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
        this.quizBuilder = new QuizBuilder(this.gateway);
    }

    /**
     * Handles starting the quiz.
     * @param request the quiz settings request model
     * @return the quiz settings response model
     */
    @Override
    public QuizSettingsResponseModel startQuiz(QuizSettingsRequestModel request) {
        this.quizSettings = new QuizSettings(request.getNumQuestions(), request.isTimerOn(),
                request.getTimerDuration(), request.isMultipleChoiceOn(), request.isTextEntryOn(),
                request.isTrueFalseOn());

        // HANDLE WEIRD INPUTS
        if (!quizSettings.atLeastOneOptionEnabled()) {
            return presenter.prepareSettingsErrorView("Invalid quiz settings: all question types are disabled.");
        } else if (!quizSettings.timerIsReasonable()) {
            return presenter.prepareSettingsErrorView("Invalid quiz settings: unreasonable time limit.");
        } else {
            // CREATE QUIZ
            this.quiz = this.quizBuilder.buildQuiz(request.getFlashcardSetID(), quizSettings);

            // GENERATING OUTPUT TO BE VIEWED
            List<QuizQuestion> quizQuestions = this.quiz.getQuizQuestions();
            ArrayList<String> questionTypes = new ArrayList<>();
            ArrayList<ArrayList<String>> outputText = new ArrayList<>();

            for (QuizQuestion quizQuestion : quizQuestions) {
                if (quizQuestion instanceof MultipleChoiceQuestion) {
                    MultipleChoiceQuestion q = (MultipleChoiceQuestion) quizQuestion;
                    questionTypes.add("MC"); // add type
                    ArrayList<String> output = new ArrayList<>(); // add question + choices under output
                    output.add((q.getQuestion()));
                    output.addAll(Arrays.asList(q.getChoices()));
                    outputText.add(output);
                } else if (quizQuestion instanceof TextEntryQuestion) {
                    TextEntryQuestion q = (TextEntryQuestion) quizQuestion;
                    questionTypes.add("TE"); // add type
                    ArrayList<String> output = new ArrayList<>(); // add term and definition (one of them is null)
                    output.add(q.getTerm());
                    output.add(q.getDefinition());
                    outputText.add(output);
                } else if (quizQuestion instanceof TrueFalseQuestion) {
                    TrueFalseQuestion q = (TrueFalseQuestion) quizQuestion;
                    questionTypes.add("TF"); // add type
                    ArrayList<String> output = new ArrayList<>(); // add term and potential definition
                    output.add(q.getTerm());
                    output.add(q.getPotentialDefinition());
                    outputText.add(output);
                }
            }
            return presenter.prepareQuizView(questionTypes, outputText);
        }
    }

    /**
     * Handles ending the quiz and getting the results.
     * @param requestModel the quiz request model
     * @return the quiz response model
     */
    @Override
    public QuizResponseModel getResults(QuizRequestModel requestModel) {
        ArrayList<String> userAnswers = requestModel.getUserAnswers();
        List<QuizQuestion> quizQuestions = quiz.getQuizQuestions();
        for (int i = 0; i < quizQuestions.size(); i++) {
            quizQuestions.get(i).setUserAnswer(userAnswers.get(i));
        }
        this.quiz.evaluate(); // evaluate the quiz
        int score = this.quiz.getScore();
        int numQuestions = this.quiz.getNumQuestions();

        if (userAnswers.contains(null) || userAnswers.contains("")) { // missing answers
            return presenter.prepareConfirmationView("Not all questions have been answered." +
                    " Are you sure you want to submit?", score, numQuestions);
        }
        return presenter.prepareResultsView(score, numQuestions);
    }

    @Override
    public QuizSettingsResponseModel getNumFlashcards(QuizSettingsRequestModel requestModel) {
        FlashcardSetDsRequestModel request = gateway.getFlashcardSet(requestModel.getFlashcardSetID());
        int numFlashcards = request.getFlashcardIds().size();
        return new QuizSettingsResponseModel(numFlashcards);
    }
}
