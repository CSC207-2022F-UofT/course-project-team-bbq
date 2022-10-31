package Entities;

public class QuizSettings {
    private boolean timerOn = false;
    private int timerDuration;

    private boolean multipleChoiceOn = true;
    private boolean textEntryOn = true;
    private boolean trueFalseOn = true;

    public QuizSettings() {

    }

    // GETTERS AND SETTERS
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
