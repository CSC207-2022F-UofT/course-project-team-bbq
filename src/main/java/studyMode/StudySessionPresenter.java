package studyMode;

public class StudySessionPresenter implements StudySessionOutputBoundary {

    @Override
    public StudySessionResponseModel prepareCardView(String outputText, int cardNumber) {
        return new StudySessionResponseModel(outputText, cardNumber);
    }

    @Override
    public StudySettingsResponseModel prepareStudyView(String outputText, String title, int numFlashcards) {
        return new StudySettingsResponseModel(outputText, title, numFlashcards);
    }

    private String reformatText(String outputText){
        // reformat the outputText to fit on the flashacard
        return null;
    }
}
