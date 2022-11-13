package Editor;

public class FlashcardEditorResponseModel {
    private String editedTerm;
    private String editedDefinition;

    private int flashcardId;

    public FlashcardEditorResponseModel(String term, String definition, int id){
        editedTerm = term;
        editedDefinition = definition;
        flashcardId = id;
    }
}
