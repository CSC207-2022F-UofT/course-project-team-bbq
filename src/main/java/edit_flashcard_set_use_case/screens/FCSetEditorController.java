package edit_flashcard_set_use_case.screens;

import edit_flashcard_set_use_case.FCSetEditorInputBoundary;
import edit_flashcard_set_use_case.FCSetEditorRequestModel;
import edit_flashcard_set_use_case.FCSetEditorResponseModel;

/**
 * Flashcard set editor controller.
 * Interface adapters (green layer).
 * @author Thomas Shim
 */
public class FCSetEditorController{
    private final FCSetEditorInputBoundary interactor;

    /**
     * Creates a new FCSetEditorController object.
     * @param interactor the interactor of the flashcard set edit use case.
     */
    public FCSetEditorController(FCSetEditorInputBoundary interactor){
        this.interactor = interactor;
    }

    /**
     * Returns the success or fail view of the edits that are given and edits the flashcard set according to the edits
     * that are given.
     * @param fcSetId id of the flashcard set to edit
     * @param titleEdit title of the flashcard set to edit
     * @param descriptionEdit definition of the flashcard set to edit
     * @return success or fail view that is defined by the FCSetEditorInputBoundary
     */
    public FCSetEditorResponseModel edit(int fcSetId, String titleEdit, String descriptionEdit){
        FCSetEditorRequestModel requestModel = new FCSetEditorRequestModel(fcSetId, titleEdit, descriptionEdit);
        return interactor.edit(requestModel);
    }
}