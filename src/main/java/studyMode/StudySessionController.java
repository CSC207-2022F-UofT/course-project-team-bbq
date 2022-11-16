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

    public StudySessionResponseModel study(StudySessionRequestModel userInput){
        return inputBoundary.study(userInput);
    }

    public StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request) {
        return inputBoundary.getSetToStudy(request);
    }
}
