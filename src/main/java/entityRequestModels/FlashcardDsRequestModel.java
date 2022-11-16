package entityRequestModels;
// use case layer

import java.time.LocalDateTime;
import java.util.List;

public class FlashcardDsRequestModel {
    private String term;
    private String definition;
    private LocalDateTime creationDate;
    private int flashcardId;
    private int belongsToId;

    public FlashcardDsRequestModel(String term, String definition, LocalDateTime creationDate,
                                   int flashcardId, int belongsToId){
        this.term = term;
        this.definition = definition;
        this.creationDate = creationDate;
        this.flashcardId = flashcardId;
        this.belongsToId = belongsToId;
    }
    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getFlashcardId(){
        return flashcardId;
    }

    public int getBelongsToId(){
        return belongsToId;
    }
    public void setTerm(String term) {
        this.term = term;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

}