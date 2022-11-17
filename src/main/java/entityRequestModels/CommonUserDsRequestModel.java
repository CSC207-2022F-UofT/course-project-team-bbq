package entityRequestModels;

import java.util.List;

public class CommonUserDsRequestModel {
    private String username;
    private String password;
    private boolean isAdmin;

    private List<Integer> flashcardSetIds;

    public CommonUserDsRequestModel(String username, String password, boolean isAdmin, List<Integer> flashcardSetIds){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSetIds = flashcardSetIds;
    }

    public String getUsername() {
        return username;
    }

    void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }
    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public List<Integer> getFlashcardSetIds(){
        return flashcardSetIds;
    }

}
