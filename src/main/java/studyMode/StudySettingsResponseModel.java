package studyMode;

public class StudySettingsResponseModel extends StudySessionResponseModel {

    private int numFlashcards;
    private String title;

    private boolean failed = false;

    public StudySettingsResponseModel(String outputText, String title, int numFlashcards) {
        super(outputText, 1);
        this.numFlashcards = numFlashcards;
        this.title = title;
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
}
