package flashcardCreator;

public interface FcCPresenter {
    FcCResponseModel prepareSuccessView(FcCResponseModel responseModel);

    FcCResponseModel prepareFailView(String error);
}