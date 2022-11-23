package edit_flashcard.screens;

import dataAccess.IFlashcardDataAccess;
import entityRequestModels.FlashcardDsRequestModel;

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
        flashcards.put(flashcard.getFlashcardId(), flashcard);
        return flashcard.getFlashcardId();
    }

    @Override
    public void editFlashcard(FlashcardDsRequestModel flashcard) {
        flashcards.replace(flashcard.getFlashcardId(), flashcard);
    }

    @Override
    public void deleteFlashcard(Integer flashcardID) {
        flashcards.remove(flashcardID);
    }
}
