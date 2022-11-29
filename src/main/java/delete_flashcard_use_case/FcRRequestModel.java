package delete_flashcard_use_case;
/**
 * Request model for flashcard removal.
 * Application business rules.
 * @author Junyu Chen
 */
public class FcRRequestModel {
    private int flashcardSetId, flashcardId;
    public FcRRequestModel(int flashcardSetId, int flashcardId){
        this.flashcardSetId = flashcardSetId;
        this.flashcardId = flashcardId;
    }

    public void setFlashcardSetId(int flashcardSetId) {
        this.flashcardSetId = flashcardSetId;
    }

    public void setFlashcardId(int flashcardId) {
        this.flashcardId = flashcardId;
    }

    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    public int getFlashcardId() {
        return flashcardId;
    }
}
