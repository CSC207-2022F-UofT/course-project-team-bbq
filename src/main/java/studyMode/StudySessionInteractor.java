package studyMode;


public class StudySessionInteractor implements StudySessionInputBoundary {

    private FlashcardStudier studier;
    private StudySessionOutputBoundary presenter;

    public StudySessionInteractor(FlashcardStudier studier,
                                  StudySessionOutputBoundary presenter){
        this.studier = studier;
        this.presenter = presenter;

    }

    @Override
    public StudySessionResponseModel study(StudySessionRequestModel userInput) {
        StudySessionResponseModel response = new StudySessionResponseModel();

        if (userInput.wantsFlip()){
            response.setOutputText(studier.flipCard());
        }
        else if (userInput.wantsNext()){
            response.setOutputText(studier.getNextCard());
        }
        else if (userInput.wantsPrev()){
            response.setOutputText(studier.getPrevCard());
        }
        response.setCardNumber(studier.getCounter() + 1);

        return response;
    }
}
