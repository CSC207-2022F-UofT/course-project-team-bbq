package quizUseCase;

/**
 * Application Business Rules
 */
public class QuizResponseModel {
    private boolean failed = false;
    private String error;

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
}
