package FlashcardCreator;

public class FcCResponseFormatter implements FcCPresenter{
    @Override
    public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public FcCResponseModel prepareFailView(String error) {
        throw  new FcCFailure(error);
    }
}
