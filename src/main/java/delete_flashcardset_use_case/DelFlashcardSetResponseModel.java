package delete_flashcardset_use_case;

// Use case (Red) layer

public class DelFlashcardSetResponseModel {

    private String message;

    public DelFlashcardSetResponseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
