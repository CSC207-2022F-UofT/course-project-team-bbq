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

    /**
     * Gets the failed value.
     * @return true if response has failed
     */
    public boolean isFailed() {
        return failed;
    }

    /**
     * Gets the message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns true if the response needs confirmation from the user.
     * @return need confirmation
     */
    public boolean needToConfirm() {
        return needConfirmation;
    }

    /**
     * Gets the score.
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the number of questions.
     * @return the number of questions
     */
    public int getNumQuestions() {
        return numQuestions;
    }

    /**
     * Gets the list of question types.
     * @return the types
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
     * Gets the user answers.
     * @return the user answers
     */
    public List<String> getUserAnswers() {
        return userAnswers;
    }

    /**
     * Gets the actual answers.
     * @return the actual answers
     */
    public List<String> getActualAnswers() {
        return actualAnswers;
    }
}
