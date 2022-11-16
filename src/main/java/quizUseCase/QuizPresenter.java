package quizUseCase;

import java.util.ArrayList;

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
