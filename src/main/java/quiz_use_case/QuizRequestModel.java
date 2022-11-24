package quiz_use_case;

import java.util.ArrayList;

/**
 * Quiz Request Model.
 * Application Business Rules
 * @author Anthony
 */
public class QuizRequestModel {
    private ArrayList<String> userAnswers;

    /**
     * Constructs a quiz request model.
     * @param userAnswers the list of user answers
     */
    public QuizRequestModel(ArrayList<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    /** GETTERS AND SETTERS **/
    public ArrayList<String> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(ArrayList<String> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
