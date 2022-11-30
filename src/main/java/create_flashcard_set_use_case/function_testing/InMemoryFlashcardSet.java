package create_flashcard_set_use_case.function_testing;

import data_access_use_case.IFlashcardSetDataAccess;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import interface_adapters.presenters.exceptions.DeleteFlashcardSetFailed;

import java.util.ArrayList;

/**
 * A mock flashcard set repository for testing purposes.
 *
 * @author Edward Ishii
 */
public class InMemoryFlashcardSet implements IFlashcardSetDataAccess {

    // Mock database
    final private ArrayList<FlashcardSetDsRequestModel> flashcardSets = new ArrayList<>();

    // helper
    private boolean checkIds(int id1, int id2) {
        return id1 == id2;
    }

    /**
     * @param flashcardSetId the id of the flashcard set.
     * @return the flashcard set associated with the id.
     */
    @Override
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId) throws DeleteFlashcardSetFailed {

        for (FlashcardSetDsRequestModel fs : flashcardSets) {
            if (checkIds(fs.getFlashcardSetId(), flashcardSetId)) {
                return fs;
            }
        }
        return null;
    }

    /**
     * @param flashcardSetId the id of the flashcard set.
     * @return the title and description of the flashcard set.
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

    /**
     * Edit the title and description of the flashcard set.
     *
     * @param flashcardSet the flashcard set to be edited.
     */
    @Override
    public void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet) {

    }

    /**
     * Save a flashcard id into the flashcard set.
     *
     * @param flashcardSetId the flashcard set's id.
     * @param flashcardId    the flashcard's id
     */
    @Override
    public void saveFlashcardID(int flashcardSetId, int flashcardId) {

    }

    /**
     * Remove a flashcard id from the flashcard set.
     *
     * @param flashcardSetId the flashcard set's id.
     * @param flashcardId    the flashcard's id.
     */
    @Override
    public void removeFlashcardId(int flashcardSetId, int flashcardId) {

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
     * @return the id of the flashcard set that's been saved
     */
    @Override
    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        flashcardSets.add(flashcardSet);
        System.out.println("Flashcard set #" + flashcardSet.getFlashcardSetId() + " has been saved.");
        return flashcardSet.getFlashcardSetId();
    }
}
