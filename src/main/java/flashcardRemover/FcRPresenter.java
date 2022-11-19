package flashcardRemover;

public interface FcRPresenter {
    FcRResponseModel prepareSuccessView(FcRResponseModel responseModel);

    FcRResponseModel prepareFailView(String error);
}
