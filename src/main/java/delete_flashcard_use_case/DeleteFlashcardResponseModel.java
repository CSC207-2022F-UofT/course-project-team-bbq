package delete_flashcard_use_case;

import java.time.LocalDateTime;

/**
 * Response model for flashcard removal.
 * Application business rules.
 * @author Junyu Chen
 */
public class DeleteFlashcardResponseModel {
    final LocalDateTime DeleteDate;
    String term;
    final String cardSetTitle;

    public DeleteFlashcardResponseModel(LocalDateTime delete, String term, String cardSetTitle) {
        this.DeleteDate = delete;
        this.term = term;
        this.cardSetTitle = cardSetTitle;
    }


    public void setTerm(String term) {
        this.term = term;
    }

    public String getCardSetName() {
        return cardSetTitle;
    }

    public String getTerm() {
        return term;
    }

    public LocalDateTime getDeleteDate() {
        return DeleteDate;
    }
}
