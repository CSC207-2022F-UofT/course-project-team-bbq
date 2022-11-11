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
        if (userInput.wantsQuit()){
            return presenter.quitStudying();
        }

        String outputText;
        if (userInput.wantsFlip()){
            outputText = studier.flipCard();
        }
        else if (userInput.wantsNext()){
            outputText = studier.getNextCard();
        }
        else if (userInput.wantsPrev()){
            outputText = studier.getPrevCard();
        }
        int cardNumber = studier.getCounter() + 1;

        return presenter.prepareStudyView(outputText, cardNumber);
    }
}
