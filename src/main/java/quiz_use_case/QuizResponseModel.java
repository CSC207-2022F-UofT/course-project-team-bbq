package quiz_use_case;

import java.util.List;

/**
 * Quiz Response Model
 * Application Business Rules
 * @author Anthony
 */
public class QuizResponseModel {
    // quiz result information
    private int score;
    private int numQuestions;
    private List<String> types;
    private List<List<String>> outputText;
    private List<String> userAnswers;
    private List<String> actualAnswers;

    // error handling
    private boolean failed = false;
    private boolean needConfirmation = false;
    private String message;

    /**
     * Constructs a quiz response model based on the following parameters.
     * @param score the quiz score
     * @param numQuestions the number of questions
     * @param types the list of question types (the type at index "i" corresponds to the question at index "i")
     * @param outputText the output text (the list at index "i" corresponds to the question at index "i")
     * @param userAnswers the list of user answers
     * @param actualAnswers the list of actual answers
     */
    public QuizResponseModel(int score, int numQuestions,
                             List<String> types,
                             List<List<String>> outputText,
                             List<String> userAnswers,
                             List<String> actualAnswers) {
        this.score = score;
        this.numQuestions = numQuestions;
        this.types = types;
        this.outputText = outputText;
        this.userAnswers = userAnswers;
        this.actualAnswers = actualAnswers;
    }

    /**
     * Constructs a quiz response model based on the following parameters.
     * @param needConfirmation true if I need to confirm
     * @param message the confirmation message
     * @param score the quiz score
     * @param numQuestions the number of questions
     * @param types the list of question types (the type at index "i" corresponds to the question at index "i")
     * @param outputText the output text (the list at index "i" corresponds to the question at index "i")
     * @param userAnswers the list of user answers
     * @param actualAnswers the list of actual answers
     */
    public QuizResponseModel(boolean needConfirmation, String message,
                             int score, int numQuestions,
                             List<String> types,
                             List<List<String>> outputText,
                             List<String> userAnswers,
                             List<String> actualAnswers) {
        this.needConfirmation = needConfirmation;
        this.message = message;
        this.score = score;
        this.numQuestions = numQuestions;
        this.types = types;
        this.outputText = outputText;
        this.userAnswers = userAnswers;
        this.actualAnswers = actualAnswers;
    }

    /**
     * Constructs a quiz response model based on the following parameters.
     * @param failed true if response has failed
     * @param message the error message
     */
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

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<List<String>> getOutputText() {
        return outputText;
    }

    public void setOutputText(List<List<String>> outputText) {
        this.outputText = outputText;
    }

    public List<String> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public List<String> getActualAnswers() {
        return actualAnswers;
    }

    public void setActualAnswers(List<String> actualAnswers) {
        this.actualAnswers = actualAnswers;
    }
}
