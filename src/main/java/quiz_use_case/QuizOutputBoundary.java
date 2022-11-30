package quiz_use_case;

import java.util.List;

/**
 * Quiz Output Boundary.
 * Application Business Rules
 * @author Anthony
 */
public interface QuizOutputBoundary {
    /**
     * Prepares the settings error view.
     * @param error the error
     * @return the quiz settings response model
     */
    QuizSettingsResponseModel prepareSettingsErrorView(String error);

    /**
     * Prepares the quiz view.
     * @param types the question types
     * @param outputText the output text
     * @param timerOn is the timer on
     * @param timerDuration the timer duration in minutes
     * @return quiz settings response model
     */
    QuizSettingsResponseModel prepareQuizView(List<String> types, List<List<String>> outputText,
                                              boolean timerOn, int timerDuration);

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
    QuizResponseModel prepareResultsView(int score, int numQuestions,
                                         List<String> types,
                                         List<List<String>> outputText,
                                         List<String> userAnswers,
                                         List<String> actualAnswers);

    /**
     * Prepares the error view.
     * @param error the error
     * @return quiz response model
     */
    QuizResponseModel prepareErrorView(String error);

    /**
     * Prepares the confirmation view.
     * @param message the message
     * @return quiz response model
     */
    QuizResponseModel prepareConfirmationView(String message, int score, int numQuestions,
                                              List<String> types,
                                              List<List<String>> outputText,
                                              List<String> userAnswers,
                                              List<String> actualAnswers);
}
