package study_mode_use_case;

/**
 * The study session output boundary
 * <p>
 * Interface Adapters layer
 * @author Lucas Prates
 */
public interface StudySessionOutputBoundary {

    /**
     * Creates a response model to update the study mode view
     * @param outputText the text to be displayed on the current flashcard
     * @param cardNumber the position of the current flashcard in the flashcard
     *                    set (count starting at 1)
     * @return a response model containing this data
     */
    StudySessionResponseModel updateStudyView(String outputText, int cardNumber);

    /**
     * Creates a response model to prepare the study mode view
     * @param outputText the text to be displayed on the current flashcard
     * @param title the title of the flashcard set
     * @param numFlashcards the total number of flashcards in the flashcard set
     * @param flashcardSetId the ID of the flashcard set
     * @return a response model containing this data
     */
    StudySettingsResponseModel prepareSuccessStudyView(String outputText, String title,
                                                       int numFlashcards, int flashcardSetId);

    /**
     * @return alert the system that preparing the study view has failed
     */
    StudySettingsResponseModel prepareFailedStudyView(String error);

}
