package Editor.FlashcardSet.screens;

import Editor.FlashcardSet.FCSetEditorOutputBoundary;
import Editor.FlashcardSet.FCSetEditorResponseModel;

public class FCSetEditorPresenter implements FCSetEditorOutputBoundary {
    @Override
    public FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public FCSetEditorResponseModel prepareFailView(String error) {throw new FCSetEditFailed(error);}
}