package study_mode_use_case;

/**
 * The study session input boundary.
 * <p>
 * Application Business Rules
 * @author Lucas Prates
 */

public interface StudySessionInputBoundary {

    /**
     * a string which tells the system that the user wants to flip the flashcard
     */
    String flip = "flip";

    /**
     * a string which tells the system that the user wants to gp to the next flashcard
     */
    String next = "next";

    /**
     * a string which tells the system that the user wants to gp to the previous flashcard
     */
    String prev = "prev";

    /**
     * a string which tells the system the user wants to shuffle randomly
     */
    String shuffleSort = "shuffle";

    /**
     * a string which tells the system the user wants to sort by alphabetical order
     */
    String alphSort = "alph";

    /**
     * a string which tells the system the user wants to sort by creation order
     */
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
