package quizUseCase;

/**
 * Application Business Rules
 */
public class QuizSettingsResponseModel {
    private boolean failed = false;
    private String error;

    private int numFlashcards;

    public QuizSettingsResponseModel() {

    }

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
}
