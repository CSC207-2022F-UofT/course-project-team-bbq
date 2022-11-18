package FlashcardRemover;

public interface FcRPresenter {
    FcRResponseModel prepareSuccessView(FcRResponseModel responseModel);

    FcRResponseModel prepareFailView(String error);
}
