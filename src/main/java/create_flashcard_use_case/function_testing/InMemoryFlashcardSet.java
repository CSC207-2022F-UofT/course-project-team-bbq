package create_flashcard_use_case.function_testing;

import data_access_use_case.IFlashcardSetDataAccess;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Database for creating and deleting use case tests.
 * @author Junyu Chen
 */
public class InMemoryFlashcardSet implements IFlashcardSetDataAccess {
    private final Map<Integer, FlashcardSetDsRequestModel> flashcardSets = new HashMap<>();
    @Override
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId) {
        return flashcardSets.get(flashcardSetId);
    }

    @Override
    public String[] getTitleAndDescription(int flashcardSetId) {
        return new String[0];
    }

    @Override
    public void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet) {

    }

    @Override
    public void saveFlashcardID(int flashcardSetId, int flashcardId) {
        FlashcardSetDsRequestModel flashcardSet = flashcardSets.get(flashcardSetId);
        List<Integer> ids = flashcardSet.getFlashcardIds();
        ids.add(flashcardId);
        flashcardSets.put(flashcardSetId, new FlashcardSetDsRequestModel(flashcardSet.getTitle(),
                flashcardSet.getDescription(), flashcardSet.getIsPrivate(), flashcardSetId,
                flashcardSet.getOwnerUsername(), ids));
    }

    @Override
    public void removeFlashcardId(int flashcardSetId, int flashcardId) {
        FlashcardSetDsRequestModel flashcardSet = flashcardSets.get(flashcardSetId);
        List<Integer> ids = flashcardSet.getFlashcardIds();
        ids.remove(flashcardId);
        flashcardSets.put(flashcardSetId, new FlashcardSetDsRequestModel(flashcardSet.getTitle(),
                flashcardSet.getDescription(), flashcardSet.getIsPrivate(), flashcardSetId,
                flashcardSet.getOwnerUsername(), ids));
    }

    @Override
    public void deleteFlashcardSet(int flashcardSetID) {

    }

    @Override
    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        flashcardSets.put(flashcardSet.getFlashcardSetId(), flashcardSet);
        return flashcardSet.getFlashcardSetId();
    }
}
