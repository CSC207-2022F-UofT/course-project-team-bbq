package quiz_use_case;

import java.util.List;

/**
 * Quiz Request Model.
 * Application Business Rules
 * @author Anthony
 */
public class QuizRequestModel {
    private final List<String> userAnswers;

    /**
     * Constructs a quiz request model.
     * @param userAnswers the list of user answers
     */
    public QuizRequestModel(List<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    /**
     * Gets the user answers.
     * @return the user answers
     */
    public List<String> getUserAnswers() {
        return userAnswers;
    }
}
