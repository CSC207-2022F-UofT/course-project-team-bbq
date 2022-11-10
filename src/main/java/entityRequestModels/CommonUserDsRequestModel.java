package entityRequestModels;

import java.util.Map;

public class CommonUserDsRequestModel {
    private String username;
    private String password;
    private Map<Integer, String[]> flashcardSets;

    public CommonUserDsRequestModel(String username, String password, Map<Integer, String[]> flashcardSets){
        this.username = username;
        this.password = password;
        this.flashcardSets = flashcardSets;
    }
}
