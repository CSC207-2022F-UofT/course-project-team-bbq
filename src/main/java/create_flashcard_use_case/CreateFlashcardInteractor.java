package create_flashcard_use_case;

import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.IFlashcardDataAccess;
import data_access_use_case.IFlashcardSetDataAccess;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;

import java.time.LocalDateTime;
/**
 * Interactor for flashcard creation.
 * Application business rules.
 * @author Junyu Chen
 */
public class CreateFlashcardInteractor implements CreateFlashcardInputBoundary {
    CreateFlashcardOutputBoundary presenter;
    IFlashcardDataAccess fcDataAccess;
    IFlashcardSetDataAccess fcsDataAccess;

    /**
     * Create FcCInteractor
     * @param gateway Database gateway.
     * @param presenter Presenter for failure or success view.
     */
    public CreateFlashcardInteractor(DBGateway gateway, CreateFlashcardOutputBoundary presenter){
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
    public CreateFlashcardResponseModel create(CreateFlashcardRequestModel requestModel) {
        if(requestModel.getDefinition().isEmpty()||requestModel.getTerm().isEmpty()){
            return presenter.prepareFailView("Term or definition is empty.");
        } else if (fcsDataAccess.getFlashcardSet(requestModel.getFlashcardSetId()) == null) {
            return presenter.prepareFailView("Flashcard set does not exist.");
        }
        LocalDateTime creationDate = LocalDateTime.now();
        FlashcardDsRequestModel flashcard = new FlashcardDsRequestModel(
                requestModel.getTerm().replace("\n", " "),
                requestModel.getDefinition().replace("\n", " "), creationDate,-1, requestModel.getFlashcardSetId());
        int flashcardId = fcDataAccess.saveFlashcard(flashcard);
        fcsDataAccess.saveFlashcardID(requestModel.getFlashcardSetId(), flashcardId);

        return presenter.prepareSuccessView(new CreateFlashcardResponseModel(creationDate, requestModel.getTerm(),
                requestModel.getDefinition()));
    }
}
