package entities;

/**
 * Stores quiz settings.
 * @author Anthony
 */
public class QuizSettings {
    private final int numQuestions;
    private final boolean timerOn;
    private final int timerDuration;
    private final boolean multipleChoiceOn;
    private final boolean textEntryOn;
    private final boolean trueFalseOn;

    /**
     * Constructor for quiz settings.
     * @param numQuestions the number of questions
     * @param timerOn true if timer is on
     * @param timerDuration the timer duration
     * @param multipleChoiceOn true if multiple choice is on
     * @param textEntryOn true if text entry is on
     * @param trueFalseOn true if true false is on
     */
    public QuizSettings(int numQuestions, boolean timerOn, int timerDuration, boolean multipleChoiceOn,
                        boolean textEntryOn, boolean trueFalseOn){
        this.numQuestions = numQuestions;
        this.timerOn = timerOn;
        this.timerDuration = timerDuration;
        this.multipleChoiceOn = multipleChoiceOn;
        this.textEntryOn = textEntryOn;
        this.trueFalseOn = trueFalseOn;
    }

    /**
     * @return true if at least one option is enabled
     */
    public boolean atLeastOneOptionEnabled() {
        return multipleChoiceOn || textEntryOn || trueFalseOn;
    }

    /**
     * @return true if the timer is between 1 and 60 or the timer is not on
     */
    public boolean timerIsReasonable() {
        return ((1 <= this.timerDuration && this.timerDuration <= 60) || !(this.timerOn));
    }

    /**
     * Gets the number of questions.
     * @return the number of questions
     */
    public int getNumQuestions() {
        return numQuestions;
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
}
