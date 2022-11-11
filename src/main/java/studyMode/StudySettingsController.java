package studyMode;

public class StudySettingsController {
    private StudySettingsInputBoundary interactor;

    public StudySettingsController(StudySettingsInputBoundary interactor){
        this.interactor = interactor;
    }

    public StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request){
        return interactor.getSetToStudy(request);
    }
}
