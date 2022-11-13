package quiz_use_case;

import entities.Quiz;
import entities.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuizInteractor implements QuizInputBoundary {
    final Quiz quiz;
    final QuizPresenter presenter;

    public QuizInteractor(Quiz quiz, QuizPresenter presenter) {
        this.quiz = quiz;
        this.presenter = presenter;
    }

    @Override
    public QuizResponseModel create(QuizRequestModel requestModel) {
        ArrayList<String> userAnswers = requestModel.getUserAnswers();
        List<QuizQuestion> quizQuestions = quiz.getQuizQuestions();
        if (userAnswers.size() != quizQuestions.size()) {
            return presenter.prepareFailView("User answers and quiz questions do not have the same size." +
                    " Not sure how you managed to accomplish this.");
        }

        for (int i = 0; i < quizQuestions.size(); i++) {
            quizQuestions.get(i).setUserAnswer(userAnswers.get(i));
        }
        QuizResponseModel results = new QuizResponseModel();
        return presenter.prepareSuccessView(results);
    }
}
