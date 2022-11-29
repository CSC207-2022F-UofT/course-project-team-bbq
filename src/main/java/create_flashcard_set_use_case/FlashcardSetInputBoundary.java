package create_flashcard_set_use_case;

// Use case (Red) layer

/**
 * The input boundary interface for creating flashcard sets.
 *
 * @author Edward Ishii
 */

public interface FlashcardSetInputBoundary {

    /**
     * Create a new flashcard set and store it into the database.
     *
     * @param requestModel the request model required for flashcard set creation.
     * @return the response model containing the newly created flashcard set data.
     */
    FlashcardSetResponseModel create(FlashcardSetRequestModel requestModel) throws FlashcardSetCreationFailed;
}
