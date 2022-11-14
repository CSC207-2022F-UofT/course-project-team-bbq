package Editor.Flashcard.screens;

import Editor.Flashcard.FlashcardEditorInputBoundary;
import Editor.Flashcard.FlashcardEditorRequestModel;
import Editor.Flashcard.FlashcardEditorResponseModel;

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
