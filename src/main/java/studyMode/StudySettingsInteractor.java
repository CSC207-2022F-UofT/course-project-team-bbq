package studyMode;

import dataAccess.DBGateway;

public class StudySettingsInteractor implements StudySettingsInputBoundary {

    FlashcardStudierBuilder builder;
    DBGateway gateway;

    StudySettingsInteractor(DBGateway gateway){
        this.gateway = gateway;
        this.builder = new FlashcardStudierBuilder(gateway);
    }

    @Override
    public StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request) {
        FlashcardStudier studier = builder.buildStudier(request.getFlashcardSetId());
        return null;
    }
}
