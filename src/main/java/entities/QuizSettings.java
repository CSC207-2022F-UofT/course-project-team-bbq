package entities;

/**
 * Stores quiz settings.
 * @author Anthony
 */
public class QuizSettings {
    private int numQuestions;
    private boolean timerOn;
    private int timerDuration;
    private boolean multipleChoiceOn;
    private boolean textEntryOn;
    private boolean trueFalseOn;

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
}
