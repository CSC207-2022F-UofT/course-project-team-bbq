package flashcardCreator.fcCScreens;

import flashcardCreator.FcCOutputBoundary;
import flashcardCreator.FcCResponseModel;

public class FcCResponsePresenter implements FcCOutputBoundary {
    @Override
    public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public FcCResponseModel prepareFailView(String error) {
        throw  new FcCFailure(error);
    }
}
