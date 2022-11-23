package edit_flashcardset.screens;

import dataAccess.IFlashcardSetDataAccess;
import entityRequestModels.FlashcardSetDsRequestModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryFlashcardSet implements IFlashcardSetDataAccess {

    private final Map<Integer, FlashcardSetDsRequestModel> flashcardSets = new HashMap<>();
    @Override
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId) {
        return flashcardSets.get(flashcardSetId);
    }

    @Override
    public String[] getTitleAndDescription(int flashcardSetId) {
        FlashcardSetDsRequestModel flashcardSet = flashcardSets.get(flashcardSetId);
        return new String[]{flashcardSet.getTitle(), flashcardSet.getDescription()};
    }

    @Override
    public void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet) {
        flashcardSets.replace(flashcardSet.getFlashcardSetId(), flashcardSet);
    }

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

    @Override
    public void deleteFlashcardSet(int flashcardSetID) {
        flashcardSets.remove(flashcardSetID);
    }

    @Override
    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        flashcardSets.put(flashcardSet.getFlashcardSetId(), flashcardSet);
        return flashcardSet.getFlashcardSetId();
    }
}
