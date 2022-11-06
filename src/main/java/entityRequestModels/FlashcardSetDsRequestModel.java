package entityRequestModels;

import java.util.List;

public class FlashcardSetDsRequestModel {
    private String title;
    private String description;
    private String isPrivate;
    private List<FlashcardDsRequestModel> flashcards;

    private String ownerUsername;
    private String flashcardSetId;

    public FlashcardSetDsRequestModel(String title, String description, String isPrivate,
                        String flashcardSetId, String ownerUsername,
                        List<FlashcardDsRequestModel> flashcards){
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.flashcardSetId = flashcardSetId;
        this.ownerUsername = ownerUsername;
        this.flashcards = flashcards;
    }
}
