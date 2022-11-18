package studyMode;

/**
 * The study session input boundary.
 * <p>
 * Application Business Rules
 * @author Lucas Prates
 */

public interface StudySessionInputBoundary {

    String flip = "flip";
    String next = "next";
    String prev = "prev";

    String shuffleSort = "shuffle";
    String alphSort = "alph";
    String timeSort = "time";

    /**
     * Flips the system to flip the current flashcard, or go to the next/prev flashcard
     *
     * @param request the user's input
     * @return a response model with the updated view data
     */
    StudySessionResponseModel study(StudySessionRequestModel request);

    /**
     * Creates an in memory FlashcardStudier for the flashcard set the user wants to study,
     * and sorts it according to the user's input.
     *
     * @param request a data-structure containing the id of the flashcard set to be studied, as
     *                well as the sorting method the user chose
     * @return a data structure with the data required to set up the study mode view
     */
    StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request);
}
