package entityRequestModels;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonUserDsRequestModel {
    private String username;
    private String password;
    private boolean isAdmin;

    private List<Integer> flashcardSetIds;
//    private Map<Integer, String[]> flashcardSets;
//    private final LocalDateTime creationTime;

    public CommonUserDsRequestModel(String username, String password, boolean isAdmin, List<Integer> flashcardSetIds){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSetIds = flashcardSetIds;
    }

    public String getName() {
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

//    public Map<Integer, String[]> getFlashcardSets(){
//        return this.flashcardSets;
//    }
//
//    public Object getCreationTime() {
//        return creationTime;
//    }
//    //public Map<Integer, String[]> getFlashcardSets() {return flashcardSets;}

}
