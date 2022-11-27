package study_mode_use_case;

/**
 * A data structure which contains the data required to set up
 * the study mode view.
 * <p>
 * Interface Adapters.
 * @author Lucas Prates
 */
public class StudySettingsResponseModel extends StudySessionResponseModel {

    private final int numFlashcards;
    private final String title;
    private final int flashcardSetId;

    /**
     * Creates a StudySettingsResponseModel
     * @param outputText the text to be displayed on the current flashcard
     * @param title the title of the current flashcard set
     * @param numFlashcards the total number of flashcards in the current flashcard set
     * @param flashcardSetId the ID of the current flashcard set
     */
    public StudySettingsResponseModel(String outputText, String title,
                                      int numFlashcards, int flashcardSetId) {
        super(outputText, 1);
        this.numFlashcards = numFlashcards;
        this.title = title;
        this.flashcardSetId = flashcardSetId;
    }

    /**
     * @return the total number of flashcards in the current flashcard set
     */
    public int getNumFlashcards() {return numFlashcards;}

    /**
     * @return the title of the current flashcard set
     */
    public String getTitle(){return title;}

    /**
     * @return the ID of the current flashcard set
     */
    public int getFlashcardSetId() {
        return flashcardSetId;
    }
}
