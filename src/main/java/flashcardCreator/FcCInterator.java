package flashcardCreator;

import dataAccess.DBGateway;
import dataAccess.IFlashcardDataAccess;
import dataAccess.IFlashcardSetDataAccess;
import entityRequestModels.FlashcardDsRequestModel;

import java.time.LocalDateTime;

public class FcCInterator implements FcCInputBoundary{
    FcCPresenter presenter;
    IFlashcardDataAccess fcDataAccess;
    IFlashcardSetDataAccess fcsDataAccess;

    public FcCInterator(DBGateway gateway, FcCPresenter presenter){
        this.presenter = presenter;
        this.fcDataAccess = gateway.getFlashcardGateway();
        this.fcsDataAccess = gateway.getFlashcardSetGateway();
    }
    @Override
    public FcCResponseModel create(FcCRequestModel requestModel) {
        if(requestModel.getDefinition().isEmpty()||requestModel.getTerm().isEmpty()){
            return presenter.prepareFailView("Term or definition is empty.");
        } else if (fcsDataAccess.getFlashcardSet(requestModel.getFlashcardSetId()) == null) {
            return presenter.prepareFailView("Flashcard set does not exist.");
        }
        LocalDateTime creationDate = LocalDateTime.now();
        FlashcardDsRequestModel flashcard = new FlashcardDsRequestModel(requestModel.getTerm(),
                requestModel.getDefinition(), creationDate,-1, requestModel.getFlashcardSetId());
        int flashcardId = fcDataAccess.saveFlashcard(flashcard);
        fcsDataAccess.saveFlashcardID(requestModel.getFlashcardSetId(), flashcardId);

        return presenter.prepareSuccessView(new FcCResponseModel(creationDate, requestModel.getTerm(),
                requestModel.getDefinition()));
    }
}
