package EditorMainPage;

public class FlashcardInfo {
    private final int id;
    private final String term;
    private final String definition;

    public FlashcardInfo(int id, String term, String definition){
        this.id = id;
        this.term = term;
        this.definition = definition;
    }

    public int getId() {
        return id;
    }

    public String getDefinition() {
        return definition;
    }

    public String getTerm() {
        return term;
    }
}
