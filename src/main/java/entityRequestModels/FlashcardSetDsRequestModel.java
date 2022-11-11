package entityRequestModels;

import java.time.LocalDateTime;
import java.util.List;

public class FlashcardSetDsRequestModel {
    private String title;
    private String description;
    private boolean isPrivate;

    private List<Integer> flashcardIds;
    private String ownerUsername;
    private int flashcardSetId;

    public FlashcardSetDsRequestModel(String title, String description, boolean isPrivate,
                        int flashcardSetId, String ownerUsername,
                        List<Integer> flashcards){
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.flashcardSetId = flashcardSetId;
        this.ownerUsername = ownerUsername;
        this.flashcardIds = flashcards;
    }

}
