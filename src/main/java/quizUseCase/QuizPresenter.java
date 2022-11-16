package quizUseCase;

/**
 * Interface Adapters
 */
public class QuizPresenter implements QuizOutputBoundary {
    /**
     * Prepares the settings error view.
     * @param error the evil error
     * @return quiz settings response model
     */
    @Override
    public QuizSettingsResponseModel prepareSettingsErrorView(String error){
        QuizSettingsResponseModel response = new QuizSettingsResponseModel();
        response.setFailed(true);
        response.setError(error);
        return response;
    }

    /**
     * Prepares the quiz view.
     * @return quiz settings response model
     */
    @Override
    public QuizSettingsResponseModel prepareQuizView() {
        return new QuizSettingsResponseModel();
    }

    /**
     * Prepares the results view.
     * @return quiz response model
     */
    @Override
    public QuizResponseModel prepareResultsView() {
        return new QuizResponseModel();
    }

    /**
     * Prepares the error view.
     * @return quiz response model
     */
    @Override
    public QuizResponseModel prepareErrorView(String error) {
        return new QuizResponseModel();
    }
}
