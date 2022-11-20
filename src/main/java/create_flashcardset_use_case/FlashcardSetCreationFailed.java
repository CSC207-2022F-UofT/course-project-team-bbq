package create_flashcardset_use_case;

public class FlashcardSetCreationFailed extends RuntimeException {
    public FlashcardSetCreationFailed(String error) {
        super(error);
    }
}
