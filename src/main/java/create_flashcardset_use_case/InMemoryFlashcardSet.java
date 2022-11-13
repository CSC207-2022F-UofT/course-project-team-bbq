package create_flashcardset_use_case;

import dataAccess.IFlashcardSetDataAccess;

import entityRequestModels.FlashcardSetDsRequestModel;

import java.util.ArrayList;


public class InMemoryFlashcardSet implements IFlashcardSetDataAccess {

    // mock database
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
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId) {

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

    /**
     * @param flashcardSetId the id of the flashcard set
     * @param title the title of the flashcard set
     * @param description the description of the flashcard set
     */
    @Override
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
        for (FlashcardSetDsRequestModel fs : flashcardSets) {
            if (checkIds(fs.getFlashcardSetId(), flashcardSetId)) {
                flashcardSets.remove(fs);
                break;
            }
        }
    }

    /**
     * @param flashcardSet the id of the flashcard set
     */
    @Override
    public void saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        flashcardSets.add(flashcardSet);
        System.out.println("Flashcard set: " + flashcardSet.getTitle() + " has been saved.");
    }
}
