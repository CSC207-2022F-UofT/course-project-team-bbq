package Editor.FlashcardSet;

public interface FCSetEditorOutputBoundary {
    FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel responseModel);

    FCSetEditorResponseModel prepareFailView(String error);
}
