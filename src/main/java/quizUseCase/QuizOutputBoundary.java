package quizUseCase;

import java.util.ArrayList;

/**
 * Application Business Rules
 */
public interface QuizOutputBoundary {
    /**
     * Prepares the settings error view.
     * @param error the evil error
     * @return quiz settings response model
     */
    QuizSettingsResponseModel prepareSettingsErrorView(String error);

    /**
     * Prepares the quiz view.
     * @return quiz settings response model
     */
    QuizSettingsResponseModel prepareQuizView(ArrayList<String> types, ArrayList<ArrayList<String>> outputText);

    /**
     * Prepares the results view.
     * @return quiz response model
     */
    QuizResponseModel prepareResultsView();

    /**
     * Prepares the error view.
     * @param error the evil error
     * @return quiz response model
     */
    QuizResponseModel prepareErrorView(String error);
}
