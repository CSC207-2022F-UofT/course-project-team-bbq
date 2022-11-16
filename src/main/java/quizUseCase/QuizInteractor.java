package quizUseCase;

import dataAccess.DBGateway;
import entities.*;
import entityRequestModels.*;

import java.util.ArrayList;
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

            // SAVE TO DATABASE
//        QuizSettingsDsRequestModel quizSettingsDsModel = new QuizSettingsDsRequestModel(
//                request.isTimerOn(),
//                request.getTimerDuration(),
//                request.isMultipleChoiceOn(),
//                request.isTextEntryOn(),
//                request.isTrueFalseOn());
            // gateway.save(quizSettingsDsModel);

            // CREATE QUIZ
            this.quiz = this.quizBuilder.buildQuiz(request.getFlashcardSetID(), quizSettings);
            return presenter.prepareQuizView();
        }
    }

    @Override
    public QuizResponseModel getResults(QuizRequestModel requestModel) {
        ArrayList<String> userAnswers = requestModel.getUserAnswers();
        List<QuizQuestion> quizQuestions = quiz.getQuizQuestions();
        if (userAnswers.size() != quizQuestions.size()) {
            return presenter.prepareErrorView("User answers and quiz questions do not have the same size.");
        }

        for (int i = 0; i < quizQuestions.size(); i++) {
            quizQuestions.get(i).setUserAnswer(userAnswers.get(i));
        }
        QuizResponseModel results = new QuizResponseModel();
        return presenter.prepareResultsView();
    }

    @Override
    public QuizSettingsResponseModel getNumFlashcards(QuizSettingsRequestModel requestModel) {
        FlashcardSetDsRequestModel request = gateway.getFlashcardSet(requestModel.getFlashcardSetID());
        int numFlashcards = request.getFlashcardIds().size();
        return new QuizSettingsResponseModel(numFlashcards);
    }
}
