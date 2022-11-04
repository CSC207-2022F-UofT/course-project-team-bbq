package entityRequestModels;
// use case layer

import java.time.LocalDateTime;

public class FlashcardDsRequestModel {
    private String term;
    private String definition;
    private String creationDate;
    private String flashcardId;
    private String belongsToId;

    // create request model from database entry
    public FlashcardDsRequestModel(String term, String definition, String creationDate,
                                   String flashcardId, String belongsToId){
        this.term = term;
        this.definition = definition;
        this.creationDate = creationDate;
        this.flashcardId = flashcardId;
        this.belongsToId = belongsToId;
    }

    // create request model from in memory flashcard
    public FlashcardDsRequestModel(String term, String definition, LocalDateTime creationDate,
                                   int flashcardId, int belongsToId){
        this.term = term;
        this.definition = definition;
        this.creationDate = creationDate.toString();
        this.flashcardId = Integer.toString(flashcardId);
        this.belongsToId = Integer.toString(belongsToId);
    }
}
