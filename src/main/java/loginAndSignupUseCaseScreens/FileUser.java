//package loginAndSignupUseCaseScreens;
//
//import dataAccess.IUserDataAccess;
//import entityRequestModels.CommonUserDsRequestModel;
//
//import java.io.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class FileUser implements IUserDataAccess {
////JUSTIN HAS IT SAVED UNDER COMMON USER DATA ACCESS CommonUserDataAccess
//    private final File csvFile;
//
//    private final Map<String, Integer> headers = new LinkedHashMap<>();
//
//    private final Map<String, CommonUserDsRequestModel> accounts = new HashMap<>();
//
//    public FileUser(String csvPath) throws IOException {
//        csvFile = new File(csvPath);
//
//        headers.put("username", 0);
//        headers.put("password", 1);
//        headers.put("isAdmin", 2);
//        headers.put("flashcardSets", 3);
//        headers.put("creation_time", 4);
//
//        if (csvFile.length() == 0) {
//            save();
//        } else {
//
//            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
//            reader.readLine(); // skip header
//
//            String row;
//            while ((row = reader.readLine()) != null) {
//                String[] col = row.split(",");
//                String username = String.valueOf(col[headers.get("username")]);
//                String password = String.valueOf(col[headers.get("password")]);
//                boolean isAdmin = Boolean.parseBoolean(String.valueOf(col[headers.get("isAdmin")])); //Edit
//
////                String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
////                LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
//                CommonUserDsRequestModel user = new CommonUserDsRequestModel(username, password, isAdmin);
//                accounts.put(username, user);
//            }
//
//            reader.close();
//        }
//    }
//
//    /**
//     * Add requestModel to storage.
//     * @param requestModel the user information to save.
//     */
//    @Override
//    public void saveUser(CommonUserDsRequestModel requestModel) {
//        accounts.put(requestModel.getName(), requestModel);
//        this.saveUser();
//    }
//    @Override
//    public void saveUser() {
//        BufferedWriter writer;
//        try {
//            writer = new BufferedWriter(new FileWriter(csvFile));
//            writer.write(String.join(",", headers.keySet()));
//            writer.newLine();
//
//            for (CommonUserDsRequestModel user : accounts.values()) {
//                String line = String.format("%s%s%s%s",
//                        user.getName(), user.getPassword(), user.getIsAdmin(), user.getFlashcardSets());
//                writer.write(line);
//                writer.newLine();
//            }
//
//            writer.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * Return whether a user exists with username identifier.
//     * @param identifier the username to check.
//     * @return whether a user exists with username identifier
//     */
//    @Override
//    public boolean existsByName(String identifier) {
//        return accounts.containsKey(identifier);
//    }
//
//
//}