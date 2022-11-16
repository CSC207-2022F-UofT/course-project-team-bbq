package quizUseCase;

/**
 * Application Business Rules
 */
public class QuizSettingsRequestModel {
    private int numQuestions;
    private boolean timerOn;
    private int timerDuration;
    private boolean multipleChoiceOn;
    private boolean textEntryOn;
    private boolean trueFalseOn;

    private int flashcardSetID;

    /**
     * Given a set of quiz settings, I want to make a request.
     * @param numQuestions the number of questions
     * @param timerOn is the timer on
     * @param timerDuration timer duration in minutes
     * @param multipleChoiceOn is the multiple choice on
     * @param textEntryOn is the text entry on
     * @param trueFalseOn is the true false on
     */
    public QuizSettingsRequestModel(int numQuestions, boolean timerOn, int timerDuration,
                                    boolean multipleChoiceOn, boolean textEntryOn, boolean trueFalseOn,
                                    int flashcardSetID) {
        this.numQuestions = numQuestions;
        this.timerOn = timerOn;
        this.timerDuration = timerDuration;
        this.multipleChoiceOn = multipleChoiceOn;
        this.textEntryOn = textEntryOn;
        this.trueFalseOn = trueFalseOn;
        this.flashcardSetID = flashcardSetID;
    }

    /**
     * Given a flashcard set ID, I want to make a request.
     * @param flashcardSetID the flashcard set ID
     */
    public QuizSettingsRequestModel(int flashcardSetID) {
        this.flashcardSetID = flashcardSetID;
    }

    /** GETTERS AND SETTERS **/
    public int getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public boolean isTimerOn() {
        return timerOn;
    }

    public void setTimerOn(boolean timerOn) {
        this.timerOn = timerOn;
    }

    public int getTimerDuration() {
        return timerDuration;
    }

    public void setTimerDuration(int timerDuration) {
        this.timerDuration = timerDuration;
    }

    public boolean isMultipleChoiceOn() {
        return multipleChoiceOn;
    }

    public void setMultipleChoiceOn(boolean multipleChoiceOn) {
        this.multipleChoiceOn = multipleChoiceOn;
    }

    public boolean isTextEntryOn() {
        return textEntryOn;
    }

    public void setTextEntryOn(boolean textEntryOn) {
        this.textEntryOn = textEntryOn;
    }

    public boolean isTrueFalseOn() {
        return trueFalseOn;
    }

    public void setTrueFalseOn(boolean trueFalseOn) {
        this.trueFalseOn = trueFalseOn;
    }

    public int getFlashcardSetID() {
        return flashcardSetID;
    }

    public void setFlashcardSetID(int flashcardSetID) {
        this.flashcardSetID = flashcardSetID;
    }
}
