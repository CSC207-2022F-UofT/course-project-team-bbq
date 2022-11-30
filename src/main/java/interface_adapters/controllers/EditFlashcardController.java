package interface_adapters.controllers;

import edit_flashcard_use_case.EditFlashcardInputBoundary;
import edit_flashcard_use_case.EditFlashcardRequestModel;
import edit_flashcard_use_case.EditFlashcardResponseModel;

/**
 * Flashcard editor controller.
 * Interface adapters (green layer).
 * @author Thomas Shim
 */
public class EditFlashcardController {

    private final EditFlashcardInputBoundary interactor;

    /**
     * Creates a new FlashcardEditorController object.
     * @param interactor the interactor of the flashcard edit use case.
     */
    public EditFlashcardController(EditFlashcardInputBoundary interactor){
        this.interactor = interactor;
    }

    /**
     * Returns the success or fail view of the edits that are given and edits the flashcard according to the edits that
     * are given.
     * @param id id of the flashcards to edit
     * @param editedTerm term of the flashcard to edit
     * @param editedDefinition definition of the flashcard to edit
     * @return success or fail view that is defined by the FlashcardEditorInputBoundary
     */
    public EditFlashcardResponseModel edit(int id, String editedTerm, String editedDefinition){
        EditFlashcardRequestModel requestModel = new EditFlashcardRequestModel(id, editedTerm, editedDefinition);

        return interactor.edit(requestModel);
    }
}