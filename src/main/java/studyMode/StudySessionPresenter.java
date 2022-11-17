package studyMode;

public class StudySessionPresenter implements StudySessionOutputBoundary {

    @Override
    public StudySessionResponseModel prepareCardView(String outputText, int cardNumber) {
        return new StudySessionResponseModel(outputText, cardNumber);
    }

    @Override
    public StudySettingsResponseModel prepareStudyView(String outputText, String title,
                                                       int numFlashcards, int flashcardSetId) {
        return new StudySettingsResponseModel(outputText, title, numFlashcards, flashcardSetId);
    }

    @Override
    public StudySettingsResponseModel prepareFailedStudyView() {
        StudySettingsResponseModel response = new StudySettingsResponseModel();
        response.setFailed(true);
        return response;
    }
}
