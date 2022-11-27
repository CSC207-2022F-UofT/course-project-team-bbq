package create_flashcard_use_case.fcCScreens;
import data_access.IFlashcardDataAccess;
import data_access.entity_request_models.FlashcardDsRequestModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InMemoryFlashcard implements IFlashcardDataAccess {
    private final Map<Integer, FlashcardDsRequestModel> flashcards = new HashMap<>();
    @Override
    public FlashcardDsRequestModel getFlashcard(Integer flashcardID) {
        return flashcards.get(flashcardID);
    }

    @Override
    public int saveFlashcard(FlashcardDsRequestModel flashcard) {
        int id;
        if (flashcards.isEmpty()){
            id = 0;
        }else{
            id = Collections.max(flashcards.keySet()) + 1;
        }
        flashcards.put(id, flashcard);
        return id;
    }

    @Override
    public void editFlashcard(FlashcardDsRequestModel flashcard) {

    }

    @Override
    public void deleteFlashcard(Integer flashcardID) {
        flashcards.remove(flashcardID);

    }
}
