package quiz_settings_use_case;

import entities.QuizSettings;

public class QuizSettingsResponseModel {
    private QuizSettings quizSettings;

    public QuizSettingsResponseModel(QuizSettings quizSettings) {
        this.quizSettings = quizSettings;
    }

    /** GETTERS AND SETTERS **/
    public QuizSettings getQuizSettings() {
        return quizSettings;
    }

    public void setQuizSettings(QuizSettings quizSettings) {
        this.quizSettings = quizSettings;
    }
}
