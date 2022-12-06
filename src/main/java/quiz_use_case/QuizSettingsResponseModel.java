package quiz_use_case;

import java.util.List;

/**
 * Quiz Settings Response Model.
 * Application Business Rules
 * @author Anthony
 */
public class QuizSettingsResponseModel {
    // information 1
    private int numFlashcards;

    // information 2
    private List<String> types;
    private List<List<String>> outputText;
    private boolean timerOn;
    private int timerDuration;

    // error handling
    private boolean failed = false;
    private String error;

    /**
     * Constructor that handles failures.
     * @param failed true if the response model has failed
     * @param error the error message
     */
    public QuizSettingsResponseModel(boolean failed, String error) {
        this.failed = failed;
        this.error = error;
    }

    /**
     * Constructor that takes in types and output text.
     * @param types the list of question types
     * @param outputText the list containing lists of output text information
     * @param timerOn is the timer on
     * @param timerDuration the timer duration in minutes
     */
    public QuizSettingsResponseModel(List<String> types, List<List<String>> outputText,
                                     boolean timerOn, int timerDuration) {
        this.types = types;
        this.outputText = outputText;
        this.timerOn = timerOn;
        this.timerDuration = timerDuration;
    }

    /**
     * Constructor that takes in the number of flashcards.
     * @param numFlashcards the number of flashcards
     */
    public QuizSettingsResponseModel(int numFlashcards) {
        this.numFlashcards = numFlashcards;
    }

    /**
     * Gets the failed value.
     * @return true if failed
     */
    public boolean isFailed() {
        return failed;
    }

    /**
     * Gets the error text.
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error text.
     * @param error the error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Gets the number of flashcards.
     * @return the number of flashcards
     */
    public int getNumFlashcards() {
        return numFlashcards;
    }

    /**
     * Gets the list of question types.
     * @return the list of question types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * Gets the output text.
     * @return the list of lists of output text
     */
    public List<List<String>> getOutputText() {
        return outputText;
    }

    /**
     * Sets the output text.
     * @param outputText the list of lists of output text
     */
    public void setOutputText(List<List<String>> outputText) {
        this.outputText = outputText;
    }

    /**
     * Gets the timer boolean value.
     * @return true if the timer is on
     */
    public boolean isTimerOn() {
        return this.timerOn;
    }

    /**
     * Gets the timer duration.
     * @return the timer duration in minutes
     */
    public int getTimerDuration() {
        return this.timerDuration;
    }
}
