package delete_flashcard_set_use_case;

/**
 * The input boundary interface for deleting flashcard sets.
 *
 * @author Edward Ishii
 */
public interface DeleteFlashcardSetInputBoundary {

    /**
     * Delete the flashcard set (and all the flashcards contained within it) from the database.
     *
     * @param requestModel the request model required for flashcard set deletion (i.e., the id).
     * @return the response model containing the deletion message.
     */
    DeleteFlashcardSetResponseModel delete(DeleteFlashcardSetRequestModel requestModel);
}
