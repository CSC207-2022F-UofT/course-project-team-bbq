package edit_flashcardset.screens;

import edit_flashcardset.FCSetEditorInputBoundary;
import edit_flashcardset.FCSetEditorRequestModel;
import edit_flashcardset.FCSetEditorResponseModel;

public class FCSetEditorController{
    private final FCSetEditorInputBoundary interactor;

    public FCSetEditorController(FCSetEditorInputBoundary interactor){
        this.interactor = interactor;
    }

    public FCSetEditorResponseModel edit(int fcSetId, String titleEdit, String descriptionEdit){
        FCSetEditorRequestModel requestModel = new FCSetEditorRequestModel(fcSetId, titleEdit, descriptionEdit);
        return interactor.edit(requestModel);
    }
}