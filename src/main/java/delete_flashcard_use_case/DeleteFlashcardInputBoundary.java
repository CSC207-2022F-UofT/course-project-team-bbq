package delete_flashcard_use_case;
/**
 * Input boundary for flashcard removal.
 * Application business rules.
 * @author Junyu Chen
 */
public interface DeleteFlashcardInputBoundary {
    DeleteFlashcardResponseModel delete(DeleteFlashcardRequestModel requestModel);
}
