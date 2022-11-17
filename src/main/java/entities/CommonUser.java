package entities;

import java.util.ArrayList;

public class CommonUser implements User{

    private String username;

    private String password;
    private boolean isAdmin;
    private String ADMIN_KEY = "BuiltDifferent";
    private ArrayList<Integer> flashcardSetIds;

    public CommonUser(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSetIds = new ArrayList<Integer>();
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean passwordIsValid() {
        return password != null && password.length() > 5;
    }

    @Override
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    @Override
    public ArrayList<Integer> getFlashcardSetIds() {
        return flashcardSetIds;
    }

    @Override
    public boolean adminKeyValid(String adminKey) {
        return adminKey.equals(ADMIN_KEY);
    }

    public void setFlashcardSetIds(ArrayList<Integer> setFlashcardSetIds) {
        this.flashcardSetIds = setFlashcardSetIds;
    }


}
