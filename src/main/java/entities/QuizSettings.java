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

    public QuizSettings(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public QuizSettings(int numQuestions, boolean timerOn, int timerDuration, boolean multipleChoiceOn,
                        boolean textEntryOn, boolean trueFalseOn){
        this.numQuestions = numQuestions;
        this.timerOn = timerOn;
        this.timerDuration = timerDuration;
        this.multipleChoiceOn = multipleChoiceOn;
        this.textEntryOn = textEntryOn;
        this.trueFalseOn = trueFalseOn;
    }

    public boolean atLeastOneOptionEnabled() {
        return multipleChoiceOn || textEntryOn || trueFalseOn;
    }

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
