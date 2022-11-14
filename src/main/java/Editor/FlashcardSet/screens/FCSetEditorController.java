package Editor.FlashcardSet.screens;

import Editor.FlashcardSet.FCSetEditorInputBoundary;
import Editor.FlashcardSet.FCSetEditorInteractor;
import Editor.FlashcardSet.FCSetEditorRequestModel;
import Editor.FlashcardSet.FCSetEditorResponseModel;

public class FCSetEditorController{
    private FCSetEditorInputBoundary interactor;

    public FCSetEditorController(FCSetEditorInputBoundary interactor){
        this.interactor = interactor;
    }

    public FCSetEditorResponseModel edit(int fcSetId, String titleEdit, String descriptionEdit){
        FCSetEditorRequestModel requestModel = new FCSetEditorRequestModel(fcSetId, titleEdit, descriptionEdit);
        return interactor.edit(requestModel);
    }
}
