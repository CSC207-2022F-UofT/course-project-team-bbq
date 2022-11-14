package dataAccess;

import entityRequestModels.FlashcardSetDsRequestModel;

import java.io.*;
import java.util.*;

import static java.lang.String.format;

public class FlashcardSetDataAccess implements IFlashcardSetDataAccess{
    private final File flashCardSetCsvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, FlashcardSetDsRequestModel> flashcardSets = new HashMap<>();

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
                String[] col = row.split(",");
                String title = String.valueOf(col[headers.get("title")]);
                String description = String.valueOf(col[headers.get("description")]);
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
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(flashCardSetCsvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (FlashcardSetDsRequestModel set : flashcardSets.values()) {
                String line = format("%s,%s,%s,%s,%s,%s", set.getTitle(), set.getDescription(), set.getIsPrivate(), set.getFlashcardSetId(), set.getOwnerUsername(),
                                set.getFlashcardIds());
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
        return flashcardSets.get(flashcardSetId);
    }

    @Override
    public String[] getTitleAndDescription(int flashcardSetId) {

        return new String[] {flashcardSets.get(flashcardSetId).getTitle(),
                flashcardSets.get(flashcardSetId).getDescription()};
    }

    @Override
    public void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet) {

    }

    @Override
    public void saveFlashcardID(int flashcardSetId, int flashcardId) {

    }

    @Override
    public void deleteFlashcardSet(int flashcardSetID) {

    }

    @Override
    public void saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {

    }
}
