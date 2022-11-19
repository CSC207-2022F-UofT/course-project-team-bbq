package studyMode;

/**
 * The controller for the study mode use case.
 * <p>
 * Interface Adapters.
 * @author Lucas Prates
 */
public class StudySessionController {
    private final StudySessionInputBoundary inputBoundary;


    public static String flip = StudySessionInputBoundary.flip;
    public static String next = StudySessionInputBoundary.next;
    public static String prev = StudySessionInputBoundary.prev;

    public static String shuffleSort = StudySessionInputBoundary.shuffleSort;

    public static String alphSort = StudySessionInputBoundary.alphSort;

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
