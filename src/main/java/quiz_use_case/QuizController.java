package quiz_use_case;

import java.util.ArrayList;

public class QuizController {
    private final QuizInputBoundary quizInput;

    public QuizController(QuizInputBoundary quizInput) {
        this.quizInput = quizInput;
    }

    QuizResponseModel create(ArrayList<String> userAnswers) {
        QuizRequestModel requestModel = new QuizRequestModel(userAnswers);

        return quizInput.create(requestModel);
    }
}
