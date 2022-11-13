package entityRequestModels;

public class QuizSettingsDsRequestModel {
    private boolean timerOn;
    private int timerDuration;
    private boolean multipleChoiceOn;
    private boolean textEntryOn;
    private boolean trueFalseOn;

    public QuizSettingsDsRequestModel(boolean timerOn, int timerDuration, boolean multipleChoiceOn,
                                    boolean textEntryOn, boolean trueFalseOn) {
        this.timerOn = timerOn;
        this.timerDuration = timerDuration;
        this.multipleChoiceOn = multipleChoiceOn;
        this.textEntryOn = textEntryOn;
        this.trueFalseOn = trueFalseOn;
    }

    /** GETTERS AND SETTERS **/
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
