package delete_flashcard_use_case;
/**
 * Presenter for flashcard remover.
 * Application business rules.
 * @author Junyu Chen
 */

public interface FcROutputBoundary {
    FcRResponseModel prepareSuccessView(FcRResponseModel responseModel);

    FcRResponseModel prepareFailView(String error);
}
