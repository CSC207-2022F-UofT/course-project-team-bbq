package quizUseCase;

import java.util.ArrayList;

/**
 * Quiz Response Model
 * Application Business Rules
 * @author Anthony
 */
public class QuizResponseModel {
    // quiz result information
    private int score;
    private int numQuestions;
    private ArrayList<String> types;
    private ArrayList<ArrayList<String>> outputText;
    private ArrayList<String> userAnswers;
    private ArrayList<String> actualAnswers;

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
                             ArrayList<String> types,
                             ArrayList<ArrayList<String>> outputText,
                             ArrayList<String> userAnswers,
                             ArrayList<String> actualAnswers) {
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
                             ArrayList<String> types,
                             ArrayList<ArrayList<String>> outputText,
                             ArrayList<String> userAnswers,
                             ArrayList<String> actualAnswers) {
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

    public ArrayList<String> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(ArrayList<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public ArrayList<String> getActualAnswers() {
        return actualAnswers;
    }

    public void setActualAnswers(ArrayList<String> actualAnswers) {
        this.actualAnswers = actualAnswers;
    }
}
