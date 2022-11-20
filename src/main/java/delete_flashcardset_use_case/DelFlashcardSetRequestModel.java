package delete_flashcardset_use_case;

// Use case (Red) layer

public class DelFlashcardSetRequestModel {

    private int flashcardSetId;

    public DelFlashcardSetRequestModel(int flashcardSetId) {
        this.flashcardSetId = flashcardSetId;
    }

    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    public void setFlashcardSetId(int flashcardSetId) {
        this.flashcardSetId = flashcardSetId;
    }

}
