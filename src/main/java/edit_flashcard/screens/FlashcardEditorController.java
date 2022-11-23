package edit_flashcard.screens;

import edit_flashcard.FlashcardEditorInputBoundary;
import edit_flashcard.FlashcardEditorRequestModel;
import edit_flashcard.FlashcardEditorResponseModel;

public class FlashcardEditorController {

    private final FlashcardEditorInputBoundary interactor;

    public FlashcardEditorController(FlashcardEditorInputBoundary interactor){
        this.interactor = interactor;
    }

    public FlashcardEditorResponseModel edit(int id, String editedTerm, String editedDefinition){
        FlashcardEditorRequestModel requestModel = new FlashcardEditorRequestModel(id, editedTerm, editedDefinition);

        return interactor.edit(requestModel);
    }
}