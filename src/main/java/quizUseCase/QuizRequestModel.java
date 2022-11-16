package quizUseCase;

import java.util.ArrayList;

/**
 * Application Business Rules
 */
public class QuizRequestModel {
    private ArrayList<String> userAnswers;

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
