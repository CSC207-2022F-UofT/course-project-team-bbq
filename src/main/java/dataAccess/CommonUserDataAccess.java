package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;

import java.io.*;
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
                boolean isAdmin = Boolean.parseBoolean((col[headers.get("isAdmin")]));
                List<Integer> flashcardSetIds = new ArrayList<>();
                for (int i = headers.get("flashcardSetIds"); i < col.length; i++){
                    flashcardSetIds.add(Integer.parseInt(col[i]));

                }

                CommonUserDsRequestModel user = new CommonUserDsRequestModel(username, password, isAdmin, flashcardSetIds);
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
                StringBuilder line = new StringBuilder(String.format("%s, %s, %s", user.getUsername(), user.getPassword(), user.getIsAdmin()));
                for(int flashcardSetIds: user.getFlashcardSetIds()){
                    line.append(",");
                    line.append(Integer.toString(flashcardSetIds));
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
    public CommonUserDsRequestModel getUser(String username) {
        return accounts.get(username);

    }

    @Override
    public Collection<CommonUserDsRequestModel> getAllUsers(){
        return accounts.values();
    }

    @Override
    public boolean existsByName(String username) {
        return accounts.containsKey(username);

    }
    @Override
    public void saveFlashcardSetID(String username, int FlashcardSetID) {
        CommonUserDsRequestModel oldUser = accounts.get(username);
        List<Integer> newFlashcardSet = new ArrayList<>(oldUser.getFlashcardSetIds());
        newFlashcardSet.add(FlashcardSetID);
        CommonUserDsRequestModel newUser = new CommonUserDsRequestModel(oldUser.getUsername(), oldUser.getPassword(), oldUser.getIsAdmin(), newFlashcardSet);

        accounts.put(username, newUser);
        save();
    }

    @Override
    public void saveUser(CommonUserDsRequestModel user) {
        accounts.put(user.getUsername(), user);
        save();

    }
}
