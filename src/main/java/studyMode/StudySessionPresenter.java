package studyMode;

import studyMode.screens.StudySettingsFailed;

/**
 * The study session presenter.
 * <p>
 * Interface adapters.
 * @author Lucas Prates
 */
public class StudySessionPresenter implements StudySessionOutputBoundary {

    @Override
    public StudySessionResponseModel updateStudyView(String outputText, int cardNumber) {
        return new StudySessionResponseModel(outputText, cardNumber);
    }

    @Override
    public StudySettingsResponseModel prepareSuccessStudyView(String outputText, String title,
                                                              int numFlashcards, int flashcardSetId) {
        return new StudySettingsResponseModel(outputText, title, numFlashcards, flashcardSetId);
    }

    @Override
    public StudySettingsResponseModel prepareFailedStudyView(String error) {
        throw new StudySettingsFailed(error);
    }
}
