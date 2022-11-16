package quizUseCase;

/**
 * Application Business Rules
 */
public interface QuizInputBoundary {
    QuizSettingsResponseModel startQuiz(QuizSettingsRequestModel requestModel);

    QuizResponseModel getResults(QuizRequestModel requestModel);

    QuizSettingsResponseModel getNumFlashcards (QuizSettingsRequestModel requestModel);
}
