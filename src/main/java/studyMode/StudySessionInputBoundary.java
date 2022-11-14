package studyMode;

public interface StudySessionInputBoundary {

    StudySessionResponseModel study(StudySessionRequestModel userInput);

    StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request);
}
