package edit_flashcardset.screens;

import dataAccess.IFlashcardSetDataAccess;
import entityRequestModels.FlashcardSetDsRequestModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An in memory Flashcard set database for the purposes of testing.
 * @author Thomas Shim
 */
public class InMemoryFlashcardSet implements IFlashcardSetDataAccess {

    private final Map<Integer, FlashcardSetDsRequestModel> flashcardSets = new HashMap<>();

    /**
     * Return the FlashcardSetDsRequestModel that corresponds with the given flashcardSetId.
     * @param flashcardSetId Flashcard set id of the wanted flashcard set.
     * @return FlashcardSetDsRequestModel of the wanted flashcard set.
     */
    @Override
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId) {
        return flashcardSets.get(flashcardSetId);
    }

    /**
     * Return a string array of the title and description of the flashcard set that corresponds with flashcardSetId.
     * @param flashcardSetId Flashcard set id of the wanted flashcard set.
     * @return String array of the title and description of the wanted flashcard. Title at index 0 and description at
     * index 1.
     */
    @Override
    public String[] getTitleAndDescription(int flashcardSetId) {
        FlashcardSetDsRequestModel flashcardSet = flashcardSets.get(flashcardSetId);
        return new String[]{flashcardSet.getTitle(), flashcardSet.getDescription()};
    }

    /**
     * Edits a flashcardSet that is already in the flashcardSet map.
     * @param flashcardSet The flashcard set information including edits that is to be edited.
     */
    @Override
    public void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet) {
        flashcardSets.replace(flashcardSet.getFlashcardSetId(), flashcardSet);
    }

    /**
     * Adds the flashcardId to the FlashcardSetDsRequestModel's list of flashcard ids corresponding to the flashcardSetId
     * given.
     * @param flashcardSetId The flashcard set to add to the list of flashcard ids.
     * @param flashcardId The flashcard id to add to the flashcard set.
     */
    @Override
    public void saveFlashcardID(int flashcardSetId, int flashcardId) {
        FlashcardSetDsRequestModel oldFlashcardSet = flashcardSets.get(flashcardSetId);
        String title = oldFlashcardSet.getTitle();
        String description = oldFlashcardSet.getDescription();
        boolean isPrivate = oldFlashcardSet.getIsPrivate();
        List<Integer> flashcardIds = oldFlashcardSet.getFlashcardIds();
        String owner = oldFlashcardSet.getOwnerUsername();

        ArrayList<Integer> newFlashcardIds = new ArrayList<>(flashcardIds);
        newFlashcardIds.add(flashcardId);
        FlashcardSetDsRequestModel newFlashcardSet = new FlashcardSetDsRequestModel(title, description, isPrivate, flashcardSetId, owner, newFlashcardIds);

        flashcardSets.put(flashcardSetId, newFlashcardSet);
    }

    /**
     * Removes the flashcardId to the FlashcardSetDsRequestModel's list of flashcard ids corresponding to the
     * flashcardSetId given.
     * @param flashcardSetId The flashcard set to delete from the list of flashcard ids.
     * @param flashcardId The flashcard id to delete in the flashcard set.
     */
    @Override
    public void removeFlashcardId(int flashcardSetId, int flashcardId) {
        FlashcardSetDsRequestModel oldFlashcardSet = flashcardSets.get(flashcardSetId);
        String title = oldFlashcardSet.getTitle();
        String description = oldFlashcardSet.getDescription();
        boolean isPrivate = oldFlashcardSet.getIsPrivate();
        List<Integer> flashcardIds = oldFlashcardSet.getFlashcardIds();
        String owner = oldFlashcardSet.getOwnerUsername();

        ArrayList<Integer> newFlashcardIds = new ArrayList<>(flashcardIds);
        newFlashcardIds.remove(flashcardId);
        FlashcardSetDsRequestModel newFlashcardSet = new FlashcardSetDsRequestModel(title, description, isPrivate, flashcardSetId, owner, newFlashcardIds);

        flashcardSets.put(flashcardSetId, newFlashcardSet);
    }

    /**
     * Deletes the flashcard set that corresponds to the flashcardSetID.
     * @param flashcardSetID the flashcard set id of the flashcard set to be deleted.
     */
    @Override
    public void deleteFlashcardSet(int flashcardSetID) {
        flashcardSets.remove(flashcardSetID);
    }

    /**
     * Add the new flashcardSet given to the map, flashcardSets.
     * @param flashcardSet the flashcard set to add to the map.
     * @return the flashcard id of the flashcard set.
     */
    @Override
    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        flashcardSets.put(flashcardSet.getFlashcardSetId(), flashcardSet);
        return flashcardSet.getFlashcardSetId();
    }
}
