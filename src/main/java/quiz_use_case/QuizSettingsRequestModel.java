package quiz_use_case;

/**
 * Quiz Settings Request Model.
 * Application Business Rules
 * @author Anthony
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

    /**
     * Gets the number of questions.
     * @return the number of questions
     */
    public int getNumQuestions() {
        return numQuestions;
    }

    /**
     * Gets the timer boolean value.
     * @return true if the timer is on
     */
    public boolean isTimerOn() {
        return timerOn;
    }

    /**
     * Gets the timer duration.
     * @return the timer duration
     */
    public int getTimerDuration() {
        return timerDuration;
    }

    /**
     * Gets the multiple choice boolean value.
     * @return true if multiple choice is on
     */
    public boolean isMultipleChoiceOn() {
        return multipleChoiceOn;
    }

    /**
     * Gets the text entry boolean value.
     * @return true if text entry is on
     */
    public boolean isTextEntryOn() {
        return textEntryOn;
    }

    /**
     * Gets the true/false boolean value.
     * @return true if true/false is on
     */
    public boolean isTrueFalseOn() {
        return trueFalseOn;
    }

    /**
     * Gets the flashcard set ID
     * @return the flashcard set ID
     */
    public int getFlashcardSetID() {
        return flashcardSetID;
    }

    /**
     * Sets the flashcard set ID
     * @param flashcardSetID the flashcard set ID
     */
    public void setFlashcardSetID(int flashcardSetID) {
        this.flashcardSetID = flashcardSetID;
    }
}
