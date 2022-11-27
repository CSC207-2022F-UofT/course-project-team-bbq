package remove_flashcard_use_case.FcRScreens;

import remove_flashcard_use_case.FcROutputBoundary;
import remove_flashcard_use_case.FcRResponseModel;
/**
 * Presenter for flashcard remover.
 * Interface adaptors.
 * @author Junyu Chen
 */

public class FcRResponsePresenter implements FcROutputBoundary {
    @Override
    public FcRResponseModel prepareSuccessView(FcRResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public FcRResponseModel prepareFailView(String error) {
        throw  new FcRFailure(error);
    }
}
