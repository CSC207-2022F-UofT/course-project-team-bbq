package quiz_use_case;

import java.util.ArrayList;

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
