package studyMode;

public class StudySettingsResponseModel extends StudySessionResponseModel {

    private int numFlashcards;
    private String title;

    private boolean failed = false;

    private int flashcardSetId;

    public StudySettingsResponseModel(String outputText, String title,
                                      int numFlashcards, int flashcardSetId) {
        super(outputText, 1);
        this.numFlashcards = numFlashcards;
        this.title = title;
        this.flashcardSetId = flashcardSetId;
    }

    public StudySettingsResponseModel(){
        super();
    }
    public int getNumFlashcards(){return numFlashcards;}

    public String getTitle(){return title;}

    public boolean hasFailed (){
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public int getFlashcardSetId() {
        return flashcardSetId;
    }
}
