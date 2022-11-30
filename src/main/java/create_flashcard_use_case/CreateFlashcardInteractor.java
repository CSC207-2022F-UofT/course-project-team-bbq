package create_flashcard_use_case;
import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;

import java.time.LocalDateTime;
/**
 * Interactor for flashcard creation.
 * Application business rules.
 * @author Junyu Chen
 */
public class CreateFlashcardInteractor implements CreateFlashcardInputBoundary {
    CreateFlashcardOutputBoundary presenter;
    DBGateway gateway;

    /**
     * Create FcCInteractor
     * @param gateway Database gateway.
     * @param presenter Presenter for failure or success view.
     */
    public CreateFlashcardInteractor(DBGateway gateway, CreateFlashcardOutputBoundary presenter){
        this.presenter = presenter;
        this.gateway = gateway;
    }

    /**
     * Create flashcard with given term, definition in the given flashcard set recording the date of creation.
     * If term or definition is empty flashcard set does not exist, returns the corresponding error.
     * @return Success or failure view.
     */
    @Override
    public CreateFlashcardResponseModel create(CreateFlashcardRequestModel requestModel) {
        String term = requestModel.getTerm().replace("\n", " ").trim();
        String definition = requestModel.getDefinition().replace("\n", " ").trim();
        if(term.isEmpty() || definition.isEmpty()){
            return presenter.prepareFailView("Term or definition is empty.");
        }
        else if (gateway.getFlashcardSet(requestModel.getFlashcardSetId()) == null) {
            return presenter.prepareFailView("Flashcard set does not exist.");
        }
        for (int flashcardId : gateway.getFlashcardSet(requestModel.getFlashcardSetId()).getFlashcardIds()){
            if (gateway.getFlashcard(flashcardId).getTerm().equals(term)){
                return new CreateFlashcardResponseModel(LocalDateTime.now(), requestModel.getTerm(),
                        gateway.getFlashcard(flashcardId).getDefinition(), flashcardId);
            }
        }
        LocalDateTime creationDate = LocalDateTime.now();
        gateway.saveFlashcard(new FlashcardDsRequestModel(term, definition, creationDate, -1,
                requestModel.getFlashcardSetId()));
        return new CreateFlashcardResponseModel(creationDate, term, definition, -1);

    }

    public CreateFlashcardResponseModel create(CreateFlashcardRequestModel requestModel, int flashcardId){
        String term = requestModel.getTerm().replace("\n", " ").trim();
        String definition = requestModel.getDefinition().replace("\n", " ").trim();
        LocalDateTime creationDate = LocalDateTime.now();
        gateway.editFlashcard(new FlashcardDsRequestModel(term, definition, creationDate, flashcardId,
                requestModel.getFlashcardSetId()));
        return new CreateFlashcardResponseModel(creationDate, term, definition, -1);
    }



}
