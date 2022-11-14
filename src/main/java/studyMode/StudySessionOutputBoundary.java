package studyMode;

public interface StudySessionOutputBoundary {

    StudySessionResponseModel prepareCardView(String outputText, int cardNumber);

    StudySettingsResponseModel prepareStudyView(String outputText, String title, int numFlashcards);

    StudySettingsResponseModel prepareFailedStudyView();

}
