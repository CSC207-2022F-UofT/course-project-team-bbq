package create_flashcard_set_use_case;

// Use case (Red) layer

import interface_adapters.presenters.exceptions.CreateFlashcardSetFailed;

/**
 * The input boundary interface for creating flashcard sets.
 *
 * @author Edward Ishii
 */

public interface CreateFlashcardSetInputBoundary {

    /**
     * Create a new flashcard set and store it into the database.
     *
     * @param requestModel the request model required for flashcard set creation.
     * @return the response model containing the newly created flashcard set data.
     */
    CreateFlashcardSetResponseModel create(CreateFlashcardSetRequestModel requestModel) throws CreateFlashcardSetFailed;
}
