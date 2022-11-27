package flashcardCreator;

import dataAccess.DBGateway;
import dataAccess.IFlashcardDataAccess;
import dataAccess.IFlashcardSetDataAccess;
import entityRequestModels.FlashcardDsRequestModel;

import java.time.LocalDateTime;
/**
 * Interactor for flashcard creator.
 * Application business rules.
 * @author Junyu Chen
 */
public class FcCInteractor implements FcCInputBoundary{
    FcCOutputBoundary presenter;
    IFlashcardDataAccess fcDataAccess;
    IFlashcardSetDataAccess fcsDataAccess;

    /**
     * Create FcCInteractor
     * @param gateway Database gateway.
     * @param presenter Presenter for failure or success view.
     */
    public FcCInteractor(DBGateway gateway, FcCOutputBoundary presenter){
        this.presenter = presenter;
        this.fcDataAccess = gateway.getFlashcardGateway();
        this.fcsDataAccess = gateway.getFlashcardSetGateway();
    }

    /**
     * Create flashcard with given term, definition in the given flashcard set recording the date of creation.
     * If term or definition is empty flashcard set does not exist, returns the corresponding error.
     * @return Success or failure view.
     */
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
