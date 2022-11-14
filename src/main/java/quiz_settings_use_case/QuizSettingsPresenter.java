package quiz_settings_use_case;

public class QuizSettingsPresenter implements QuizSettingsOutputBoundary {
    @Override
    public QuizSettingsResponseModel prepareSuccessView() {
        return null;
    }

    @Override
    public QuizSettingsResponseModel prepareFailView(String error){
        return null;
    }
}
