package edit_flashcard_use_case.screens;

import data_access_use_case.IFlashcardDataAccess;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import java.util.HashMap;
import java.util.Map;

/**
 * An in memory Flashcard database for the purposes of testing.
 * @author Thomas Shim
 */
public class InMemoryFlashcard implements IFlashcardDataAccess {

    private final Map<Integer, FlashcardDsRequestModel> flashcards = new HashMap<>();

    /**
     * Return the FlashcardDsRequestModel that corresponds with the given flashcardId.
     * @param flashcardID Flashcard id of the wanted flashcard.
     * @return FlashcardDsRequestModel of the wanted flashcard.
     */
    @Override
    public FlashcardDsRequestModel getFlashcard(Integer flashcardID) {
        return flashcards.get(flashcardID);
    }

    /**
     * Adds the given flashcard to the flashcards map and returns the flashcardId of the given flashcard.
     * @param flashcard The flashcard that is wanted to be saved.
     * @return the id of the flashcard that is saved.
     */
    @Override
    public int saveFlashcard(FlashcardDsRequestModel flashcard) {
        flashcards.put(flashcard.getFlashcardId(), flashcard);
        return flashcard.getFlashcardId();
    }

    /**
     * Edits a flashcard that is already in the flashcards map.
     * @param flashcard The flashcard information including edits that is to be edited.
     */
    @Override
    public void editFlashcard(FlashcardDsRequestModel flashcard) {
        flashcards.replace(flashcard.getFlashcardId(), flashcard);
    }

    /**
     * Deletes the flashcard that corresponds with the given flashcardId.
     * @param flashcardID The id of the flashcard that is to be deleted.
     */
    @Override
    public void deleteFlashcard(Integer flashcardID) {
        flashcards.remove(flashcardID);
    }
}
