package delete_flashcardset_use_case;

import dataAccess.IFlashcardDataAccess;
import entityRequestModels.FlashcardDsRequestModel;

import java.util.ArrayList;

public class InMemoryFlashcard implements IFlashcardDataAccess {

    // Mock database
    final private ArrayList<FlashcardDsRequestModel> flashcards = new ArrayList<>();

    // helper
    private boolean checkIds(int id1, int id2) {
        return id1 == id2;
    }

    @Override
    public FlashcardDsRequestModel getFlashcard(Integer flashcardID) {
        for (FlashcardDsRequestModel fc : flashcards) {
            if (checkIds(fc.getFlashcardId(), flashcardID)) {
                return fc;
            }
        }
        return null;
    }

    @Override
    public int saveFlashcard(FlashcardDsRequestModel flashcard) {
        flashcards.add(flashcard);
        System.out.println("Flashcard [" + flashcard.getTerm() + "] has been saved.");
        return flashcard.getFlashcardId();
    }

    @Override
    public void editFlashcard(FlashcardDsRequestModel flashcard) {

    }

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
