package quizUseCase;

import java.util.ArrayList;

/**
 * Quiz Settings Response Model.
 * Application Business Rules
 * @author Anthony
 */
public class QuizSettingsResponseModel {
    // information 1
    private int numFlashcards;

    // information 2
    private ArrayList<String> types;
    private ArrayList<ArrayList<String>> outputText;

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
     */
    public QuizSettingsResponseModel(ArrayList<String> types, ArrayList<ArrayList<String>> outputText) {
        this.types = types;
        this.outputText = outputText;
    }

    /**
     * Constructor that takes in the number of flashcards.
     * @param numFlashcards the number of flashcards
     */
    public QuizSettingsResponseModel(int numFlashcards) {
        this.numFlashcards = numFlashcards;
    }

    /** GETTERS AND SETTERS **/
    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getNumFlashcards() {
        return numFlashcards;
    }

    public void setNumFlashcards(int numFlashcards) {
        this.numFlashcards = numFlashcards;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public ArrayList<ArrayList<String>> getOutputText() {
        return outputText;
    }

    public void setOutputText(ArrayList<ArrayList<String>> outputText) {
        this.outputText = outputText;
    }
}
