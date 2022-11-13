package quiz_use_case;

public interface QuizPresenter {
    QuizResponseModel prepareSuccessView(QuizResponseModel results);

    QuizResponseModel prepareFailView(String error);
}
