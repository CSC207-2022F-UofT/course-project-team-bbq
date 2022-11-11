package dataAccess;

import entityRequestModels.FlashcardSetDsRequestModel;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FlashcardSetDataAccess implements IFlashcardSetDataAccess{
    private final File flashCardSetCsvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, FlashcardSetDsRequestModel> accounts = new HashMap<>();

    public FlashcardSetDataAccess(String csvPath) throws IOException {
        flashCardSetCsvFile = new File(csvPath);

        headers.put("title", 0);
        headers.put("description", 1);
        headers.put("privacy", 2);
        headers.put("id", 3);
        headers.put("owner", 4);
        headers.put("flashcards", 5);

        if (flashCardSetCsvFile.length() == 0) {
            save();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(flashCardSetCsvFile));
            reader.readLine(); // skip header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String title = String.valueOf(col[headers.get("title")]);
                String description = String.valueOf(col[headers.get("description")]);
                boolean privacy = Boolean.parseBoolean(col[headers.get("privacy")]);
                int id = Integer.parseInt(col[headers.get("id")]);
                String ownerUsername = String.valueOf(col[headers.get("owner")]);

                List<Integer> flashcardIDs = new ArrayList<>();
                for (int i=6; i <= col.length; i++){
                    if (flashcardIDs.size() == 0){
                        flashcardIDs.add(Integer.parseInt(col[headers.get("flashcards")]));
                    }
                    else{
                        flashcardIDs.add(Integer.parseInt(col[i]));
                    }
                }


                FlashcardSetDsRequestModel set = new FlashcardSetDsRequestModel(title, description, privacy, id, ownerUsername, flashcardIDs);
                accounts.put(ownerUsername, set);
            }

            reader.close();
        }
    }
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(flashCardSetCsvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (FlashcardSetDsRequestModel set : accounts.values()) {
                String line = "%s,%s,%s".
                        formatted(set.getName(), set.getPassword(), set.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId) {
        return null;
    }

    @Override
    public String[] getTitleAndDescription(int flashcardSetId) {
        return new String[0];
    }

    @Override
    public void editTitleAndDescription(int flashcardSetId, String title, String description) {

    }

    @Override
    public void saveFlashcardID(int flashcardSetID, int flashcardID) {

    }

    @Override
    public void deleteFlashcardSet(int flashcardSetID) {

    }

    @Override
    public void saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {

    }
}
