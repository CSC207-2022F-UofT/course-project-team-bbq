package entityRequestModels;
// use case layer

import java.time.LocalDateTime;

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
}
