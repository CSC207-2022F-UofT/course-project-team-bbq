package studyMode;

public class StudySessionController {
    private StudySessionInputBoundary inputBoundary;

    public StudySessionController(StudySessionInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public StudySessionResponseModel study(StudySessionRequestModel userInput){
        return inputBoundary.study(userInput);
    }
}
