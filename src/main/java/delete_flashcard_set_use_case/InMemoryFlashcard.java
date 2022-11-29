package delete_flashcard_set_use_case;

import data_access.IFlashcardDataAccess;
import data_access.entity_request_models.FlashcardDsRequestModel;

import java.util.ArrayList;

/**
 * A mock flashcard repository for testing purposes.
 *
 * @author Edward Ishii
 */
public class InMemoryFlashcard implements IFlashcardDataAccess {

    // Mock database
    final private ArrayList<FlashcardDsRequestModel> flashcards = new ArrayList<>();

    // helper
    private boolean checkIds(int id1, int id2) {
        return id1 == id2;
    }

    /**
     * @param flashcardID the id of the flashcard.
     * @return the flashcard associated with the id.
     */
    @Override
    public FlashcardDsRequestModel getFlashcard(Integer flashcardID) {
        for (FlashcardDsRequestModel fc : flashcards) {
            if (checkIds(fc.getFlashcardId(), flashcardID)) {
                return fc;
            }
        }
        return null;
    }

    /**
     * @param flashcard the flashcard that is to be saved.
     * @return the id of the flashcard that was saved.
     */
    @Override
    public int saveFlashcard(FlashcardDsRequestModel flashcard) {
        flashcards.add(flashcard);
        System.out.println("Flashcard [" + flashcard.getTerm() + "] has been saved.");
        return flashcard.getFlashcardId();
    }

    /**
     * @param flashcard the flashcard that is to be edited.
     */
    @Override
    public void editFlashcard(FlashcardDsRequestModel flashcard) {

    }

    /**
     * @param flashcardID the id of the flashcard.
     */
    @Override
    public void deleteFlashcard(Integer flashcardID) {
        boolean deleted = false;
        for (FlashcardDsRequestModel fc : flashcards) {
            if (checkIds(fc.getFlashcardId(), flashcardID)) {
                flashcards.remove(fc);
                deleted = true;
                break;
            }
        }
        if (deleted) {
            System.out.println("Flashcard #" + flashcardID + " has been deleted");
        }
    }
}
