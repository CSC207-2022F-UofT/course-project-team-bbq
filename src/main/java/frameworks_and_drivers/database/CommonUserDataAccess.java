package frameworks_and_drivers.database;

import data_access_use_case.IUserDataAccess;
import data_access_use_case.entity_request_models.CommonUserDsRequestModel;

import java.io.*;
import java.util.*;
/**
 * Common User Request Model.
 * Application Business Rules
 * @author Justin Li
 */

public class CommonUserDataAccess implements IUserDataAccess {
    private final File userCsvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, CommonUserDsRequestModel> accounts = new HashMap<>();

    /**
     * Creates a common user data access based on the following parameters.
     * @param csvPath the csv file pathway to the database.
     */
    public CommonUserDataAccess(String csvPath) throws IOException {
        userCsvFile = new File(csvPath);

        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("isAdmin", 2);
        headers.put("flashcardSetIds", 3);

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
    /**
     * A private function that is called in the methods below which saves any changes made to a common user.
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(userCsvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (CommonUserDsRequestModel user : accounts.values()) {
                StringBuilder line = new StringBuilder(String.format("%s,%s,%s", user.getUsername(), user.getPassword(), user.getIsAdmin()));
                for(int flashcardSetIds: user.getFlashcardSetIds()){
                    line.append(",");
                    line.append(flashcardSetIds);
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
     * Gets the user object with a given username.
     * @param username the user's username.
     * @return the user object with the given username.
     */
    @Override
    public CommonUserDsRequestModel getUser(String username) {
        return accounts.get(username);
    }
    /**
     * Gets all the user objects in the database.
     * @return all the user objects in the database.
     */
    @Override
    public Collection<CommonUserDsRequestModel> getAllUsers(){
        return accounts.values();
    }
    /**
     * Returns true if a user object containing given username exists.
     * @param username the user's username
     * @return true if a user object containing given username exists.
     */
    @Override
    public boolean existsByName(String username) {
        return accounts.containsKey(username);

    }
    /**
     * Saves a new flashcard set id into the list of current flashcard set ids that the user has created.
     * @param username the user's username
     * @param flashcardSetId the id of the flashcard set that the user created.
     */
    @Override
    public void saveFlashcardSetID(String username, int flashcardSetId) {
        CommonUserDsRequestModel oldUser = accounts.get(username);
        List<Integer> newFlashcardSet = new ArrayList<>(oldUser.getFlashcardSetIds());
        newFlashcardSet.add(flashcardSetId);
        CommonUserDsRequestModel newUser = new CommonUserDsRequestModel(oldUser.getUsername(), oldUser.getPassword(), oldUser.getIsAdmin(), newFlashcardSet);

        accounts.put(username, newUser);
        save();
    }
    /**
     * Deletes a flashcard set created by a user.
     * @param username the user's username
     * @param flashcardSetId the id of the flashcard set that will be deleted
     */
    @Override
    public void deleteFlashcardSetID(String username, int flashcardSetId) {
        CommonUserDsRequestModel oldUser = accounts.get(username);
        List<Integer> newFlashcardSet = new ArrayList<>(oldUser.getFlashcardSetIds());
        newFlashcardSet.remove((Object) flashcardSetId);
        CommonUserDsRequestModel newUser = new CommonUserDsRequestModel(oldUser.getUsername(), oldUser.getPassword(), oldUser.getIsAdmin(), newFlashcardSet);

        accounts.put(username, newUser);
        save();
    }
    /**
     * Saves a newly created user to the database
     * @param user the user's username
     */
    @Override
    public void saveUser(CommonUserDsRequestModel user) {
        accounts.put(user.getUsername(), user);
        save();

    }
}
