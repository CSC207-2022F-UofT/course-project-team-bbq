package flashcardRemover;

import java.time.LocalDateTime;

public class FcRResponseModel {
    LocalDateTime DeleteDate;
    String term;

    public FcRResponseModel(LocalDateTime delete, String term) {
        this.DeleteDate = delete;
        this.term = term;
    }


    public void setTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public LocalDateTime getDeleteDate() {
        return DeleteDate;
    }
}
