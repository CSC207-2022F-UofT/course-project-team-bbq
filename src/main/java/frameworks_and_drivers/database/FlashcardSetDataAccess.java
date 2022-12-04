package frameworks_and_drivers.database;

import data_access_use_case.IFlashcardSetDataAccess;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;

import java.io.*;
import java.util.*;

import static java.util.Collections.max;

public class FlashcardSetDataAccess implements IFlashcardSetDataAccess {
    private final File flashCardSetCsvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, FlashcardSetDsRequestModel> flashcardSets = new HashMap<>();

    /**
     * Creates a flashcard set data access object based on the following parameters.
     * @param csvPath the csv file pathway to the database.
     */
    public FlashcardSetDataAccess(String csvPath) throws IOException {
        flashCardSetCsvFile = new File(csvPath);

        headers.put("title", 0);
        headers.put("description", 1);
        headers.put("isPrivate", 2);
        headers.put("flashcardSetId", 3);
        headers.put("ownerUsername", 4);
        headers.put("flashcardIds", 5);

        if (flashCardSetCsvFile.length() == 0) {
            save();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(flashCardSetCsvFile));
            reader.readLine(); // skip header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",(?=([^\"]|\"[^\"]*\")*$)");
                String title = String.valueOf(col[headers.get("title")]).replace("\"","");
                String description = String.valueOf(col[headers.get("description")]).replace("\"","");
                boolean privacy = Boolean.parseBoolean(col[headers.get("isPrivate")]);
                int id = Integer.parseInt(col[headers.get("flashcardSetId")]);
                String ownerUsername = String.valueOf(col[headers.get("ownerUsername")]);

                List<Integer> flashcardIds = new ArrayList<>();
                for (int i=headers.get("flashcardIds"); i < col.length; i++){
                    flashcardIds.add(Integer.parseInt(col[i]));
                    }

                FlashcardSetDsRequestModel set = new FlashcardSetDsRequestModel(title, description, privacy, id, ownerUsername, flashcardIds);
                flashcardSets.put(id, set);
            }

            reader.close();
        }
    }
    /**
     * A private function that is called in the methods below which saves any changes made to a flashcard set.
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(flashCardSetCsvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (FlashcardSetDsRequestModel set : flashcardSets.values()) {
                StringBuilder line = new StringBuilder(String.
                        format("\"%s\",\"%s\",%s,%s,%s", set.getTitle(), set.getDescription(), set.getIsPrivate(),
                                set.getFlashcardSetId(), set.getOwnerUsername()));
                for(int flashcardIds : set.getFlashcardIds()){
                    line.append(",");
                    line.append(flashcardIds);
                }
                writer.write(line.toString());
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Gets the flashcard set request model containing the given flashcard set id.
     * @param flashcardSetId the id of the flashcard set.
     * @return the flashcard set request model containing the given flashcard set id.
     */
    @Override
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId) {
        return flashcardSets.get(flashcardSetId);
    }

    /**
     * Gets the title and description of the flashcard set
     * @param flashcardSetId the id of the flashcard set.
     * @return the tile and description of the flashcard set
     */
    @Override
    public String[] getTitleAndDescription(int flashcardSetId) {
        return new String[] {flashcardSets.get(flashcardSetId).getTitle(),
                flashcardSets.get(flashcardSetId).getDescription()};
    }

    /**
     * Edits the title and description of the flashcard set by edits given and rewrites the flashcard set database.
     * @param flashcardSet the flashcard set object.
     */
    @Override
    public void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet) {
        int id = flashcardSet.getFlashcardSetId();
        flashcardSets.replace(id, flashcardSet);
        save();
    }
    /**
     * Saves the newly created flashcard id into the list of flashcard ids contained in the flashcard set.
     * @param flashcardSetId the id of the flashcard set.
     * @param flashcardId the id of the flashcard that will be added.
     */
    @Override
    public void saveFlashcardID(int flashcardSetId, int flashcardId) {
        FlashcardSetDsRequestModel oldFlashcardSet = flashcardSets.get(flashcardSetId);
        List<Integer> newFlashcards = new ArrayList<>(oldFlashcardSet.getFlashcardIds());
        newFlashcards.add(flashcardId);
        FlashcardSetDsRequestModel newFlashcardSet = new FlashcardSetDsRequestModel(
                oldFlashcardSet.getTitle(),
                oldFlashcardSet.getDescription(),
                oldFlashcardSet.getIsPrivate(),
                flashcardSetId, oldFlashcardSet.getOwnerUsername(), newFlashcards);

        flashcardSets.put(flashcardSetId, newFlashcardSet);
        save();
    }
    /**
     * Removes a flashcard id from the list of flashcard ids contained in the flashcard set.
     * @param flashcardSetId the id of the flashcard set.
     * @param flashcardId the id of the flashcard that will be removed.
     */
    @Override
    public void removeFlashcardId(int flashcardSetId, int flashcardId){
        FlashcardSetDsRequestModel oldFlashcardSet = flashcardSets.get(flashcardSetId);
        List<Integer> newFlashcards = new ArrayList<>(oldFlashcardSet.getFlashcardIds());
        newFlashcards.remove(Integer.valueOf(flashcardId));
        FlashcardSetDsRequestModel newFlashcardSet = new FlashcardSetDsRequestModel(
                oldFlashcardSet.getTitle(),
                oldFlashcardSet.getDescription(),
                oldFlashcardSet.getIsPrivate(),
                flashcardSetId, oldFlashcardSet.getOwnerUsername(), newFlashcards);

        flashcardSets.put(flashcardSetId, newFlashcardSet);
        save();
    }
    /**
     * Deletes a flashcard set containing the given id.
     * @param flashcardSetID the id of the flashcard set that will be deleted.
     */
    @Override
    public void deleteFlashcardSet(int flashcardSetID) {
        flashcardSets.remove(flashcardSetID);
        save();
    }
    /**
     * Saves a newly created flashcard set with a unique id into the database.
     * @param flashcardSet the flashcard set object.
     * @return the id of the newly created flashcard set.
     */
    @Override
    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        int id = getLargestId() + 1;
        flashcardSet.setFlashcardSetId(id);
        flashcardSets.put(id, flashcardSet);
        save();
        return id;
    }
    /**
     * Gets the largest id contained in a flashcard set in the database.
     * @return the largest id contained in a flashcard set in the database.
     */
    private int getLargestId(){
        if (flashcardSets.size() == 0) {
            return -1;
        }
        Set<Integer> ids = flashcardSets.keySet();
        return max(ids);
    }
}
