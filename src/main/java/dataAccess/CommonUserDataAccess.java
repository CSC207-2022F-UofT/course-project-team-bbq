package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.UserDsRequestModel;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommonUserDataAccess implements IUserDataAccess{
    private final File userCsvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, CommonUserDsRequestModel> accounts = new HashMap<>();

    public CommonUserDataAccess(String csvPath) throws IOException {
        userCsvFile = new File(csvPath);

        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("flashcardSets", 2);

        if (userCsvFile.length() == 0) {
            save();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(userCsvFile));
            reader.readLine(); // skip header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String username = String.valueOf(col[headers.get("username")]);
                String password = String.valueOf(col[headers.get("password")]);


                CommonUserDsRequestModel user = new CommonUserDsRequestModel(username, password, );
                accounts.put(username, user);
            }

            reader.close();
        }
    }
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(userCsvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (CommonUserDsRequestModel user : accounts.values()) {
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
    public UserDsRequestModel getUser(String username) {
        return null;
    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public void saveFlashcardsetID(String username, int FlashcardSetID) {

    }

    @Override
    public void saveUser(UserDsRequestModel user) {

    }
}
