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

    /**
     * If flashcardId is -1, try to create a new flashcard.
     * If flashcardId is not -1, try to create a new flashcard at the given flashcard id.
     * @param term term of the flashcard
     * @param definition definition of the flashcard
     * @param flashcardId flashcard id to write to, -1 is a placeholder meaning no given flashcard id.
     * @return CreateFlashcardResponseModel
     */
    public CreateFlashcardResponseModel create(String term, String definition, int flashcardId){
        if (flashcardId == -1){
            return inputBoundary.create(new CreateFlashcardRequestModel(flashcardSetId, term, definition));
        }
        return inputBoundary.create(new CreateFlashcardRequestModel(flashcardSetId, term, definition), flashcardId);
    }
}
