package create_flashcard_use_case.fcCScreens;

import create_flashcard_use_case.FcCOutputBoundary;
import create_flashcard_use_case.FcCResponseModel;

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
