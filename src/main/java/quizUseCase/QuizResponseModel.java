package quizUseCase;

/**
 * Application Business Rules
 */
public class QuizResponseModel {
    // quiz result information
    private int score;
    private int numQuestions;

    // errors
    private boolean failed = false;
    private boolean needConfirmation = false;
    private String message;

    public QuizResponseModel(int score, int numQuestions) {
        this.score = score;
        this.numQuestions = numQuestions;
    }

    public QuizResponseModel(boolean needConfirmation, String message, int score, int numQuestions) {
        this.needConfirmation = needConfirmation;
        this.message = message;
        this.score = score;
        this.numQuestions = numQuestions;
    }

    public QuizResponseModel(boolean failed, String message) {
        this.failed = failed;
        this.message = message;
    }

    /** GETTERS AND SETTERS **/
    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean needToConfirm() {
        return needConfirmation;
    }

    public void setNeedConfirmation(boolean needConfirmation) {
        this.needConfirmation = needConfirmation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }
}
