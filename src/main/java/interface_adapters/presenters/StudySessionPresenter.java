package interface_adapters.presenters;

import interface_adapters.presenters.exceptions.StudySettingsFailed;
import study_mode_use_case.StudySessionOutputBoundary;
import study_mode_use_case.StudySessionResponseModel;
import study_mode_use_case.StudySettingsResponseModel;

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
