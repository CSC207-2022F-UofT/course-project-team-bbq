package studyMode;

public class StudySessionController {
    private StudySessionInputBoundary inputBoundary;

    public StudySessionController(StudySessionInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public void study(StudySessionRequestModel userInput){
        if (userInput.wantsFlip()){
            inputBoundary.flipCard();
        }
        else if (userInput.wantsNext()){
            inputBoundary.getNextCard();
        }
        else if (userInput.wantsPrev()){
            inputBoundary.getPrevCard();
        }
        else if (userInput.wantsQuit()){
            inputBoundary.quitStudying();
        }
    }
}
