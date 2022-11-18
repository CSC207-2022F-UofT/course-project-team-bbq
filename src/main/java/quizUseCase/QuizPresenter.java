package quizUseCase;

import java.util.ArrayList;

/**
 * Quiz Presenter.
 * Interface Adapters
 * @author Anthony
 */
public class QuizPresenter implements QuizOutputBoundary {
    /**
     * Prepares the settings error view.
     * @param error the evil error
     * @return quiz settings response model
     */
    @Override
    public QuizSettingsResponseModel prepareSettingsErrorView(String error){
        return new QuizSettingsResponseModel(true, error);
    }

    /**
     * Prepares the quiz view.
     * @return quiz settings response model
     */
    @Override
    public QuizSettingsResponseModel prepareQuizView(ArrayList<String> types,
                                                     ArrayList<ArrayList<String>> outputText) {
        return new QuizSettingsResponseModel(types, outputText);
    }

    /**
     * Prepares the results view.
     * @return quiz response model
     */
    @Override
    public QuizResponseModel prepareResultsView(int score, int numQuestions,
                                                ArrayList<String> types,
                                                ArrayList<ArrayList<String>> outputText,
                                                ArrayList<String> userAnswers,
                                                ArrayList<String> actualAnswers) {
        return new QuizResponseModel(score, numQuestions, types, outputText, userAnswers, actualAnswers);
    }

    /**
     * Prepares the error view.
     * @return quiz response model
     */
    @Override
    public QuizResponseModel prepareErrorView(String error) {
        return new QuizResponseModel(true, error);
    }

    /**
     * Prepares the confirmation view.
     * @param message the message
     * @return quiz response model
     */
    @Override
    public QuizResponseModel prepareConfirmationView(String message,
                                                     int score, int numQuestions,
                                                     ArrayList<String> types,
                                                     ArrayList<ArrayList<String>> outputText,
                                                     ArrayList<String> userAnswers,
                                                     ArrayList<String> actualAnswers) {
        return new QuizResponseModel(true, message, score, numQuestions,
                types, outputText, userAnswers, actualAnswers);
    }
}
