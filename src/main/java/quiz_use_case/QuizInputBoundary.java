package quiz_use_case;

/**
 * Quiz Input Boundary.
 * Application Business Rules
 * @author Anthony
 */
public interface QuizInputBoundary {
    /**
     * Handles starting the quiz.
     * @param requestModel the quiz settings request model
     * @return the quiz settings response model
     */
    QuizSettingsResponseModel startQuiz(QuizSettingsRequestModel requestModel);

    /**
     * Handles getting the results.
     * @param requestModel the quiz request model
     * @return the quiz response model
     */
    QuizResponseModel getResults(QuizRequestModel requestModel);

    /**
     * Handles getting the number of flashcards.
     * @param requestModel the quiz settings request model
     * @return the quiz settings response model
     */
    QuizSettingsResponseModel getNumFlashcards(QuizSettingsRequestModel requestModel);
}
