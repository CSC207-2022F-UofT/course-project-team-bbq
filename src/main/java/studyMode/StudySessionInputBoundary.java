package studyMode;

public interface StudySessionInputBoundary {

    String flip = "flip";
    String next = "next";
    String prev = "prev";

    String shuffleSort = "shuffle";
    String alphSort = "alph";
    String timeSort = "time";

    StudySessionResponseModel study(StudySessionRequestModel userInput);

    StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request);
}
