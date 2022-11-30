package interface_adapters.presenters;

import quiz_use_case.QuizOutputBoundary;
import quiz_use_case.QuizResponseModel;
import quiz_use_case.QuizSettingsResponseModel;

import java.util.List;

/**
 * Quiz Presenter.
 * Interface Adapters
 * @author Anthony
 */
public class QuizPresenter implements QuizOutputBoundary {
    /**
     * Prepares the settings error view.
     * @param error the error
     * @return quiz settings response model
     */
    @Override
    public QuizSettingsResponseModel prepareSettingsErrorView(String error){
        return new QuizSettingsResponseModel(true, error);
    }

    /**
     * Prepares the quiz view.
     * @param types the question types
     * @param outputText the output text
     * @param timerOn is the timer on
     * @param timerDuration the timer duration in minutes
     * @return quiz settings response model
     */
    @Override
    public QuizSettingsResponseModel prepareQuizView(List<String> types,
                                                     List<List<String>> outputText,
                                                     boolean timerOn, int timerDuration) {
        return new QuizSettingsResponseModel(types, outputText, timerOn, timerDuration);
    }


    /**
     * Prepares the quiz results view.
     * @param score         the score
     * @param numQuestions  the number of questions
     * @param types         the question types
     * @param outputText    the output text
     * @param userAnswers   the user answers
     * @param actualAnswers the actual answers
     * @return the quiz response model
     */
    @Override
    public QuizResponseModel prepareResultsView(int score, int numQuestions,
                                                List<String> types,
                                                List<List<String>> outputText,
                                                List<String> userAnswers,
                                                List<String> actualAnswers) {
        return new QuizResponseModel(score, numQuestions, types, outputText, userAnswers, actualAnswers);
    }

    /**
     * Prepares the error view.
     * @param error the error
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
                                                     List<String> types,
                                                     List<List<String>> outputText,
                                                     List<String> userAnswers,
                                                     List<String> actualAnswers) {
        return new QuizResponseModel(true, message, score, numQuestions,
                types, outputText, userAnswers, actualAnswers);
    }
}
