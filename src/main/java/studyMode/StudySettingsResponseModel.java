package studyMode;

public class StudySettingsResponseModel extends StudySessionResponseModel {

    int numFlashcards;
    String title;
    public StudySettingsResponseModel(String outputText, String title, int numFlashcards) {
        super(outputText, 1);
        this.numFlashcards = numFlashcards;
        this.title = title;
    }

    public int getNumFlashcards(){return numFlashcards;}

    public String getTitle(){return title;}
}
