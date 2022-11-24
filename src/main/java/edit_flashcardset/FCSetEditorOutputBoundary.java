package edit_flashcardset;

/**
 * Flashcard set editor output boundary.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public interface FCSetEditorOutputBoundary {
    FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel responseModel);

    FCSetEditorResponseModel prepareFailView(String error);
}