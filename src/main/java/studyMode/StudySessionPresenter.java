package studyMode;

public class StudySessionPresenter implements StudySessionOutputBoundary {

    @Override
    public StudySessionResponseModel prepareStudyView(String outputText, int cardNumber) {
        return new StudySessionResponseModel(outputText, cardNumber);
    }

    @Override
    public StudySessionResponseModel quitStudying() {
        return null;
    }
}
