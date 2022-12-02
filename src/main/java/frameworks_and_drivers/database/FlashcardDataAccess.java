package frameworks_and_drivers.database;

import data_access_use_case.IFlashcardDataAccess;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Collections.max;

public class FlashcardDataAccess implements IFlashcardDataAccess {
    private final File flashCardCsvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, FlashcardDsRequestModel> flashcards = new HashMap<>();
    /**
     * Creates a flashcard data access object based on the following parameters.
     * @param csvPath the csv file pathway to the database.
     */
    public FlashcardDataAccess(String csvPath) throws IOException {
        flashCardCsvFile = new File(csvPath);

        headers.put("term", 0);
        headers.put("definition", 1);
        headers.put("creationDate", 2);
        headers.put("flashcardId", 3);
        headers.put("belongsToId", 4);

        if (flashCardCsvFile.length() == 0) {
            save();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(flashCardCsvFile));
            reader.readLine(); // skip header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String term = String.valueOf(col[headers.get("term")]);
                String definition = String.valueOf(col[headers.get("definition")]);
                String creationDateText = String.valueOf(col[headers.get("creationDate")]);
                LocalDateTime creationDate = LocalDateTime.parse(creationDateText);
                int flashcardId = Integer.parseInt(col[headers.get("flashcardId")]);
                int belongsToId = Integer.parseInt(col[headers.get("belongsToId")]);

                FlashcardDsRequestModel card = new FlashcardDsRequestModel(term, definition, creationDate, flashcardId, belongsToId);
                flashcards.put(flashcardId, card);
            }

            reader.close();
        }
    }
    /**
     * A private function that is called in the methods below which saves any changes made to a flashcard.
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(flashCardCsvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (FlashcardDsRequestModel set : flashcards.values()) {
                String line = String.
                        format("%s,%s,%s,%s,%s", set.getTerm(), set.getDefinition(), set.getCreationDate(), set.getFlashcardId(),
                                set.getBelongsToId());

                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the flashcard request model containing a given flashcard id.
     * @param flashcardId the id of the flashcard.
     * @return the flashcard request model containing a given flashcard id.
     */
    @Override
    public FlashcardDsRequestModel getFlashcard(Integer flashcardId) {
        return flashcards.get(flashcardId);
    }
    /**
     * Saves a newly created flashcard into the database with a unique id.
     * @param flashcard the flashcard object that will be created.
     * @return the id of the flashcard saved.
     */
    @Override
    public int saveFlashcard(FlashcardDsRequestModel flashcard) {
        int id = getLargestId() + 1;
        flashcard.setFlashcardId(id);
        flashcards.put(id, flashcard);
        save();
        return id;
    }

    /**
     * Gets the largest id contained in a flashcard in the database.
     * @return the largest id contained in a flashcard in the database.
     */
    private int getLargestId(){
        Set<Integer> ids = flashcards.keySet();
        return max(ids);
    }
    /**
     * Edits the flashcard by edits given and rewrites the flashcard database.
     * @param flashcard the flashcard request model that will be edited
     */
    @Override
    public void editFlashcard(FlashcardDsRequestModel flashcard) {
        int id = flashcard.getFlashcardId();
        flashcards.replace(id, flashcard);
        save();
    }
    /**
     * Deletes the flashcard containing the given id and rewrites the flashcard database.
     * @param flashcardID the id of the flashcard that will be deleted.
     */
    @Override
    public void deleteFlashcard(Integer flashcardID) {
        flashcards.remove(flashcardID);
        save();
    }
}
