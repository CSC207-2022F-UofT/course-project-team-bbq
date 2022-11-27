package quiz_use_case;

import dataAccess.DBGateway;
import entities.*;
import entityRequestModels.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Quiz Interactor.
 * Application Business Rules
 * @author Anthony
 */
public class QuizInteractor implements QuizInputBoundary {
    private Quiz quiz;

    // information
    private ArrayList<String> questionTypes;
    private ArrayList<ArrayList<String>> outputText;

    // output boundary
    private final QuizOutputBoundary presenter;

    // data access
    private final DBGateway gateway;

    // bob the builder
    private final QuizBuilder quizBuilder;

    /**
     * Constructs a quiz interactor.
     * @param gateway the gateway
     * @param presenter the quiz output boundary presenter
     */
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
        // entities
        QuizSettings quizSettings = new QuizSettings(request.getNumQuestions(), request.isTimerOn(),
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
            this.questionTypes = new ArrayList<>();
            this.outputText = new ArrayList<>();

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
        ArrayList<String> actualAnswers = quiz.getActualAnswers();
        this.quiz.setUserAnswers(userAnswers);
        this.quiz.evaluate(); // evaluate the quiz
        int score = this.quiz.getScore();
        int numQuestions = this.quiz.getNumQuestions();

        if (userAnswers.contains(null) || userAnswers.contains("")) { // missing answers
            String message = "Not all questions have been answered. Are you sure you want to submit?";
            return presenter.prepareConfirmationView(message, score, numQuestions,
                    questionTypes, outputText, userAnswers, actualAnswers);
        }
        return presenter.prepareResultsView(score, numQuestions,
                questionTypes, outputText, userAnswers, actualAnswers);
    }

    /**
     * Handles getting the number of flashcards.
     * @param requestModel the quiz settings request model
     * @return the quiz settings response model
     */
    @Override
    public QuizSettingsResponseModel getNumFlashcards(QuizSettingsRequestModel requestModel) {
        FlashcardSetDsRequestModel request = gateway.getFlashcardSet(requestModel.getFlashcardSetID());
        int numFlashcards = request.getFlashcardIds().size();
        return new QuizSettingsResponseModel(numFlashcards);
    }
}
