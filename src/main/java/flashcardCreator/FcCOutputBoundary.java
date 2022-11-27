package flashcardCreator;
/**
 * Presenter for flashcard creator.
 * Application business rules.
 * @author Junyu Chen
 */
public interface FcCOutputBoundary {
    FcCResponseModel prepareSuccessView(FcCResponseModel responseModel);

    FcCResponseModel prepareFailView(String error);
}
