package quiz_settings_use_case;

public interface QuizSettingsOutputBoundary {
    QuizSettingsResponseModel prepareSuccessView();

    QuizSettingsResponseModel prepareFailView(String error);
}
