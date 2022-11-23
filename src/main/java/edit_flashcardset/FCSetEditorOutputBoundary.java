package edit_flashcardset;

public interface FCSetEditorOutputBoundary {
    FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel responseModel);

    FCSetEditorResponseModel prepareFailView(String error);
}