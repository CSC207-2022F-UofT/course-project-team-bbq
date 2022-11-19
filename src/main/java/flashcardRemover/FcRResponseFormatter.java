package flashcardRemover;

public class FcRResponseFormatter implements FcRPresenter{
    @Override
    public FcRResponseModel prepareSuccessView(FcRResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public FcRResponseModel prepareFailView(String error) {
        throw  new FcRFailure(error);
    }
}
