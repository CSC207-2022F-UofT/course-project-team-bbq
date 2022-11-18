package quizUseCase;

import java.util.ArrayList;

/**
 * Application Business Rules
 */
public interface QuizOutputBoundary {
    /**
     * Prepares the settings error view.
     * @param error the error
     * @return quiz settings response model
     */
    QuizSettingsResponseModel prepareSettingsErrorView(String error);

    /**
     * Prepares the quiz view.
     * @param types the list of question types
     * @param outputText the text to be displayed
     * @return quiz settings response model
     */
    QuizSettingsResponseModel prepareQuizView(ArrayList<String> types, ArrayList<ArrayList<String>> outputText);

    /**
     * Prepares the results view.
     * @param score the score
     * @param numQuestions the number of questions
     * @return quiz response model
     */
    QuizResponseModel prepareResultsView(int score, int numQuestions);

    /**
     * Prepares the quiz error view.
     * @param error the error
     * @return quiz response model
     */
    QuizResponseModel prepareErrorView(String error);

    /**
     * Prepares the confirmation view.
     * @param message the message
     * @return quiz response model
     */
    QuizResponseModel prepareConfirmationView(String message, int score, int numQuestions);
}
