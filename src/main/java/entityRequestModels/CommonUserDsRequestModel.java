package entityRequestModels;

import java.time.LocalDateTime;
import java.util.Map;

public class CommonUserDsRequestModel {
    private String username;
    private String password;
    private String repeatPassword;
    private boolean isAdmin;
    private Map<Integer, String[]> flashcardSets;
    private final LocalDateTime creationTime;

    public CommonUserDsRequestModel(String username, String password,
                                    Map<Integer, String[]> flashcardSets,
                                    LocalDateTime creationTime){
        this.username = username;
        this.password = password;
        //this.isAdmin = isAdmin;
        this.flashcardSets = flashcardSets;
        this.creationTime = creationTime;
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
