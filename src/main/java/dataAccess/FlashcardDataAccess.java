package dataAccess;

import entityRequestModels.FlashcardDsRequestModel;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FlashcardDataAccess implements IFlashcardDataAccess{
    private final File flashCardCsvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, FlashcardDsRequestModel> flashcards = new HashMap<>();

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
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(flashCardCsvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (FlashcardDsRequestModel set : flashcards.values()) {
                String line = String.
                        format(set.getTerm(), set.getDefinition(), set.getCreationDate(), set.getFlashcardId(),
                                set.getBelongsToId());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public FlashcardDsRequestModel getFlashcard(Integer flashcardID) {
        return flashcards.get(flashcardID);
    }

    @Override
    public void saveFlashcard(FlashcardDsRequestModel flashcard) {

    }

    @Override
    public void editFlashcard(FlashcardDsRequestModel flashcard) {

    }

    @Override
    public void deleteFlashcard(Integer flashcardID) {

    }
}
