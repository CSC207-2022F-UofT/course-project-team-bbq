package quiz_use_case;

import java.util.ArrayList;

/**
 * Quiz Request Model.
 * Application Business Rules
 * @author Anthony
 */
public class QuizRequestModel {
    private final ArrayList<String> userAnswers;

    /**
     * Constructs a quiz request model.
     * @param userAnswers the list of user answers
     */
    public QuizRequestModel(ArrayList<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    /**
     * Gets the user answers.
     * @return the user answers
     */
    public ArrayList<String> getUserAnswers() {
        return userAnswers;
    }
}
