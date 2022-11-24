package edit_flashcard.screens;

import edit_flashcard.FlashcardEditorInputBoundary;
import edit_flashcard.FlashcardEditorRequestModel;
import edit_flashcard.FlashcardEditorResponseModel;

/**
 * Flashcard editor controller.
 * Interface adapters (green layer).
 * @author Thomas Shim
 */
public class FlashcardEditorController {

    private final FlashcardEditorInputBoundary interactor;

    /**
     * Creates a new FlashcardEditorController object.
     * @param interactor the interactor of the flashcard edit use case.
     */
    public FlashcardEditorController(FlashcardEditorInputBoundary interactor){
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
    public FlashcardEditorResponseModel edit(int id, String editedTerm, String editedDefinition){
        FlashcardEditorRequestModel requestModel = new FlashcardEditorRequestModel(id, editedTerm, editedDefinition);

        return interactor.edit(requestModel);
    }
}