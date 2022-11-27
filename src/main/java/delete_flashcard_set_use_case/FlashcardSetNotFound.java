package delete_flashcard_set_use_case;

public class FlashcardSetNotFound extends RuntimeException {

    public FlashcardSetNotFound(String error) {
        super(error);
    }
}
