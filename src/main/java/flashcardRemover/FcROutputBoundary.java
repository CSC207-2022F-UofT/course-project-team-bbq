package flashcardRemover;
/**
 * Presenter for flashcard remover.
 * Application business rules.
 * @author Junyu Chen
 */

public interface FcROutputBoundary {
    FcRResponseModel prepareSuccessView(FcRResponseModel responseModel);

    FcRResponseModel prepareFailView(String error);
}
