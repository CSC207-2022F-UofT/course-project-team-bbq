package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.UserDsRequestModel;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

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
                List<Integer> flashcardSetIds = new ArrayList<>();
                for (int i = headers.get("flashcardSetIds"); i < col.length; i++){
                    flashcardSetIds.add(Integer.parseInt(col[i]));

                }

                CommonUserDsRequestModel user = new CommonUserDsRequestModel(username, password, flashcardSetIds);
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
                String line = String.format(user.getUsername(), user.getPassword(), user.getFlashcardSetIds());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public CommonUserDsRequestModel getUser(String username) {
        return null;
    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public void saveFlashcardSetID(String username, int FlashcardSetID) {

    }

    @Override
    public void saveUser(CommonUserDsRequestModel user) {

    }
}
