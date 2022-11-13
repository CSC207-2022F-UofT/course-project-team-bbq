package quiz_settings_use_case;

public interface QuizSettingsPresenter {
    QuizSettingsResponseModel prepareSuccessView(QuizSettingsResponseModel quizSettings);

    QuizSettingsResponseModel prepareFailView(String error);
}
