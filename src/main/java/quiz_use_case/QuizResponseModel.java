package quiz_use_case;

import entities.Quiz;

public class QuizResponseModel {
    private Quiz quiz;

    public QuizResponseModel(Quiz quiz) {
        this.quiz = quiz;
    }

    /** GETTERS AND SETTERS **/
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
