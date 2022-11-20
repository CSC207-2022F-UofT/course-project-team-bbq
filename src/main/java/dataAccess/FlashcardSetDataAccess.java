package dataAccess;

import entityRequestModels.FlashcardSetDsRequestModel;

import java.io.*;
import java.util.*;

import static java.util.Collections.max;

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
                StringBuilder line = new StringBuilder(String.
                        format("%s,%s,%s,%s,%s", set.getTitle(), set.getDescription(), set.getIsPrivate(), set.getFlashcardSetId(), set.getOwnerUsername(),
                                set.getFlashcardIds()));
                for(int flashcardIds : set.getFlashcardIds()){
                    line.append(",");
                    line.append(Integer.toString(flashcardIds));
                }
                writer.write(line.toString());
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
        int id = flashcardSet.getFlashcardSetId();
        flashcardSets.replace(id, flashcardSet);
        save();
    }

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

    @Override
    public void deleteFlashcardSet(int flashcardSetID) {
        flashcardSets.remove(flashcardSetID);
        save();
    }

    @Override
    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        int id = getLargestId() + 1;
        flashcardSet.setFlashcardSetId(id);
        flashcardSets.put(id, flashcardSet);
        save();
        return id;
    }

    private int getLargestId(){
        if (flashcardSets.size() == 0) {
            return -1;
        }
        Set<Integer> ids = flashcardSets.keySet();
        return max(ids);
    }
}
