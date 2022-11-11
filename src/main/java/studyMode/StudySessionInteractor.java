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
        String outputText=null;
        String command = userInput.getCommand();

        switch (command) {
            case "flip":
                outputText = studier.flipCard();
                break;
            case "next":
                outputText = studier.getNextCard();
                break;
            case "prev":
                outputText = studier.getPrevCard();
                break;
        }
        int cardNumber = studier.getCounter() + 1;

        return presenter.prepareStudyView(outputText, cardNumber);

    }
}
