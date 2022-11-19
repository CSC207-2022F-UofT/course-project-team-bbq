package flashcardRemover;

import dataAccess.DBGateway;
import dataAccess.IFlashcardDataAccess;
import dataAccess.IFlashcardSetDataAccess;

import java.time.LocalDateTime;

public class FcRInterator implements FcRInputBoundary{
    FcRPresenter presenter;
    IFlashcardDataAccess fcDataAccess;
    IFlashcardSetDataAccess fcsDataAccess;

    public FcRInterator(DBGateway gateway, FcRPresenter presenter){
        this.presenter = presenter;
        this.fcDataAccess = gateway.getFlashcardGateway();
        this.fcsDataAccess = gateway.getFlashcardSetGateway();
    }
    @Override
    public FcRResponseModel delete(FcRRequestModel requestModel) {
        String term;
        if (fcDataAccess.getFlashcard(requestModel.getFlashcardId()) == null){
            presenter.prepareFailView("Flashcard do not exist");
        }
        if (fcsDataAccess.getFlashcardSet(requestModel.getFlashcardSetId()) == null){
            presenter.prepareFailView("Flashcard set do not exist");
        }
        term = fcDataAccess.getFlashcard(requestModel.getFlashcardId()).getTerm();
        fcDataAccess.deleteFlashcard(requestModel.getFlashcardId());
        fcsDataAccess.removeFlashcardId(requestModel.getFlashcardSetId(), requestModel.getFlashcardId());
        return presenter.prepareSuccessView(new FcRResponseModel(LocalDateTime.now(), term));
    }
}
