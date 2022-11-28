package study_mode_use_case;

/**
 * The controller for the study mode use case.
 * <p>
 * Interface Adapters.
 * @author Lucas Prates
 */
public class StudySessionController {
    /**
     * an interactor which controls the use case
     */
    private final StudySessionInputBoundary inputBoundary;


    /**
     * a string which tells the system that the user wants to flip the flashcard
     */
    public static String flip = StudySessionInputBoundary.flip;

    /**
     * a string which tells the system that the user wants to gp to the next flashcard
     */
    public static String next = StudySessionInputBoundary.next;

    /**
     * a string which tells the system that the user wants to gp to the previous flashcard
     */
    public static String prev = StudySessionInputBoundary.prev;

    /**
     * a string which tells the system the user wants to shuffle randomly
     */
    public static String shuffleSort = StudySessionInputBoundary.shuffleSort;

    /**
     * a string which tells the system the user wants to sort by alphabetical order
     */
    public static String alphSort = StudySessionInputBoundary.alphSort;

    /**
     * a string which tells the system the user wants to sort by creation order
     */
    public static String timeSort = StudySessionInputBoundary.timeSort;

    /**
     * Creates a StudySessionController
     * @param inputBoundary the StudySettingsInputBoundary
     */
    public StudySessionController(StudySessionInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * Tells the system to flip the current flashcard, or go to the next/prev flashcard.
     *
     * @param request the user's input
     * @return a response model with the updated view data
     */
    public StudySessionResponseModel study(StudySessionRequestModel request){
        return inputBoundary.study(request);
    }

    /**
     * Tells the system the user wants to begin studying a flashcard set, specifying how the
     * flashcard set should be sorted.
     *
     * @param request a data-structure containing the id of the flashcard set to be studied, as
     *                well as the sorting method the user chose
     * @return a data structure with the data required to set up the study mode view
     */
    public StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request) {
        return inputBoundary.getSetToStudy(request);
    }
}
