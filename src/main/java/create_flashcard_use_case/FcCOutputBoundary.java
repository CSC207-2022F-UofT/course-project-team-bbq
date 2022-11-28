package create_flashcard_use_case;
/**
 * Presenter for flashcard creation.
 * Application business rules.
 * @author Junyu Chen
 */
public interface FcCOutputBoundary {
    FcCResponseModel prepareSuccessView(FcCResponseModel responseModel);

    FcCResponseModel prepareFailView(String error);
}
