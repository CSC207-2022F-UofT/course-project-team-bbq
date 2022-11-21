package delete_flashcardset_use_case;

public class FlashcardSetNotFound extends RuntimeException {

    public FlashcardSetNotFound(String error) {
        super(error);
    }
}
