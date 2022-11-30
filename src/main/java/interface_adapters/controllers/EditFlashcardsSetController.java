package interface_adapters.controllers;

import edit_flashcard_set_use_case.EditFlashcardSetInputBoundary;
import edit_flashcard_set_use_case.EditFlashcardSetRequestModel;
import edit_flashcard_set_use_case.EditFlashcardSetResponseModel;

/**
 * Flashcard set editor controller.
 * Interface adapters (green layer).
 * @author Thomas Shim
 */
public class EditFlashcardsSetController {
    private final EditFlashcardSetInputBoundary interactor;

    /**
     * Creates a new FCSetEditorController object.
     * @param interactor the interactor of the flashcard set edit use case.
     */
    public EditFlashcardsSetController(EditFlashcardSetInputBoundary interactor){
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
    public EditFlashcardSetResponseModel edit(int fcSetId, String titleEdit, String descriptionEdit){
        EditFlashcardSetRequestModel requestModel = new EditFlashcardSetRequestModel(fcSetId, titleEdit, descriptionEdit);
        return interactor.edit(requestModel);
    }
}