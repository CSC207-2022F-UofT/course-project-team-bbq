package studyMode;

public interface StudySessionOutputBoundary {

    StudySessionResponseModel prepareStudyView(String outputText, int cardNumber);

    StudySessionResponseModel quitStudying();
}
