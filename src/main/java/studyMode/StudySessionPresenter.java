package studyMode;

public class StudySessionPresenter implements StudySessionOutputBoundary {
    @Override
    public StudySessionViewModel prepareStudyView(StudySessionResponseModel output) {
       String outputText = output.getOutputText();
       String cardNumber = ( (Integer) output.getCardNumber()).toString();

       return new StudySessionViewModel(outputText, cardNumber);
    }
}
