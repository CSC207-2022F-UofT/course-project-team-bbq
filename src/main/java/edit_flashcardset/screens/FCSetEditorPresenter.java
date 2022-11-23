package edit_flashcardset.screens;

import edit_flashcardset.FCSetEditorOutputBoundary;
import edit_flashcardset.FCSetEditorResponseModel;

public class FCSetEditorPresenter implements FCSetEditorOutputBoundary {
    @Override
    public FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public FCSetEditorResponseModel prepareFailView(String error) {throw new FCSetEditFailed(error);}
}