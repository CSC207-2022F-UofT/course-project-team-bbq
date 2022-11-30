package interface_adapters.controllers;

import create_flashcard_use_case.CreateFlashcardInputBoundary;
import create_flashcard_use_case.CreateFlashcardRequestModel;
import create_flashcard_use_case.CreateFlashcardResponseModel;

/**
 * Controller for flashcard creation.
 * Interface adaptors.
 * @author Junyu Chen
 */

public class CreateFlashcardController {
    CreateFlashcardInputBoundary inputBoundary;
    int flashcardSetId;

    /**
     * Create FcCController
     * @param inputBoundary Interactor for flashcard creator.
     * @param flashcardSetId ID of flashcard set which new flashcard will be created in.
     */
    public CreateFlashcardController(CreateFlashcardInputBoundary inputBoundary, int flashcardSetId){
        this.inputBoundary = inputBoundary;
        this.flashcardSetId = flashcardSetId;
    }
    public CreateFlashcardResponseModel create(String term, String definition){
        return inputBoundary.create(new CreateFlashcardRequestModel(flashcardSetId, term, definition));
    }
}
