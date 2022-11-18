package studyMode;

public class StudySessionController {
    private final StudySessionInputBoundary inputBoundary;

    public static String flip = StudySessionInputBoundary.flip;
    public static String next = StudySessionInputBoundary.next;
    public static String prev = StudySessionInputBoundary.prev;

    public static String shuffleSort = StudySessionInputBoundary.shuffleSort;

    public static String alphSort = StudySessionInputBoundary.alphSort;

    public static String timeSort = StudySessionInputBoundary.timeSort;

    public StudySessionController(StudySessionInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * @param userInput tells the system if the user wants to flip the flashcard
     *                  or go to the next / previous flashcard
     * @return a data structure containing the new text to display on the flashcard,
     * as well as the current card number.
     */
    public StudySessionResponseModel study(StudySessionRequestModel userInput){
        return inputBoundary.study(userInput);
    }

    /**
     * @param request tells the system the flashcard set the user wants to study,
     *                how the cards should be sorted, and whether the term or description
     *                of the flashcards should be displayed first by default
     * @return a data structure which tells the system if the flashcard set was successfully
     * fetched from the database, the length and title of the flashcard set, and initial text
     * to be written to the first flashcard set.
     */
    public StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request) {
        return inputBoundary.getSetToStudy(request);
    }
}
