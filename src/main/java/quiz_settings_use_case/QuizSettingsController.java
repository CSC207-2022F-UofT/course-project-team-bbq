package quiz_settings_use_case;

public class QuizSettingsController {
    private final QuizSettingsInputBoundary quizSettingsInput;

    public QuizSettingsController(QuizSettingsInputBoundary quizSettingsInput) {
        this.quizSettingsInput = quizSettingsInput;
    }

    QuizSettingsResponseModel create(int numQuestions, boolean timerOn, int timerDuration,
                                     boolean multipleChoiceOn, boolean textEntryOn, boolean trueFalseOn) {
        QuizSettingsRequestModel requestModel = new QuizSettingsRequestModel(numQuestions, timerOn, timerDuration,
                multipleChoiceOn, textEntryOn, trueFalseOn);

        return quizSettingsInput.create(requestModel);
    }
}
