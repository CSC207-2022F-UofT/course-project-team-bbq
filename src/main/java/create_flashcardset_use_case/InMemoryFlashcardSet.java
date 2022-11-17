package create_flashcardset_use_case;

import dataAccess.IFlashcardSetDataAccess;
import delete_flashcardset_use_case.FlashcardSetNotFound;
import entityRequestModels.FlashcardSetDsRequestModel;

import java.util.ArrayList;


public class InMemoryFlashcardSet implements IFlashcardSetDataAccess {

    // Mock database
    final private ArrayList<FlashcardSetDsRequestModel> flashcardSets = new ArrayList<>();

    // helper
    private boolean checkIds(int id1, int id2) {
        return id1 == id2;
    }

    /**
     * @param flashcardSetId the id of the flashcard set
     * @return the flashcard set associated with the id
     */
    @Override
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId) throws FlashcardSetNotFound {

        for (FlashcardSetDsRequestModel fs : flashcardSets) {
            if (checkIds(fs.getFlashcardSetId(), flashcardSetId)) {
                return fs;
            }
        }
        return null;
    }

    /**
     * @param flashcardSetId the id of the flashcard set
     * @return the title and description of the flashcard set
     */
    @Override
    public String[] getTitleAndDescription(int flashcardSetId) {
        String[] toReturn = new String[2];
        for (FlashcardSetDsRequestModel fs : flashcardSets) {
            if (checkIds(fs.getFlashcardSetId(), flashcardSetId)) {
                toReturn[0] = fs.getTitle();
                toReturn[1] = fs.getDescription();
            }
        }
        return toReturn;
    }

    @Override
    public void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet) {

    }

    @Override
    public void saveFlashcardID(int flashcardSetId, int flashcardId) {

    }

//    @Override
//    public void saveFlashcardID(FlashcardSetDsRequestModel flashcardSet) {
//
//    }

    /**
     * @param flashcardSetId the id of the flashcard set
     * @param title the title of the flashcard set
     * @param description the description of the flashcard set
     */

    public void editTitleAndDescription(int flashcardSetId, String title, String description) {
        for (FlashcardSetDsRequestModel fs : flashcardSets) {
            if (checkIds(fs.getFlashcardSetId(), flashcardSetId)) {
                fs.setTitle(title);
                fs.setDescription(description);
            }
        }
    }

    /**
     * @param flashcardSetId the id of the flashcard set
     */
    @Override
    public void deleteFlashcardSet(int flashcardSetId) {
        boolean deleted = false;
        for (FlashcardSetDsRequestModel fs : flashcardSets) {
            if (checkIds(fs.getFlashcardSetId(), flashcardSetId)) {
                flashcardSets.remove(fs);
                deleted = true;
                break;
            }
        }
        if (deleted) {
            System.out.println("Flashcard set #" + flashcardSetId + " has been deleted");
        }
    }

    /**
     * @param flashcardSet the id of the flashcard set
     * @return
     */
    @Override
    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        flashcardSets.add(flashcardSet);
        System.out.println("Flashcard set #" + flashcardSet.getFlashcardSetId() + " has been saved.");
        return flashcardSet.getFlashcardSetId();
    }
}
