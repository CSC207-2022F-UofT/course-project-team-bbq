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
    final CreateFlashcardOutputBoundary presenter;
    final DBGateway gateway;

    /**
     * Create CreateFlashcard Interactor
     * @param gateway Database gateway.
     * @param presenter Presenter for failure or success view.
     */
    public CreateFlashcardInteractor(DBGateway gateway, CreateFlashcardOutputBoundary presenter){
        this.presenter = presenter;
        this.gateway = gateway;
    }

    /**
     * Try to create a flashcard with the given term and definition in the given flashcard set, recording the date of creation.
     * If the term or definition is empty flashcard set does not exist, return the corresponding error.
     * If there is already a flashcard with the same term, return the response model representing the existing flashcard.
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
                return presenter.prepareDuplicateView(new CreateFlashcardResponseModel(LocalDateTime.now(), requestModel.getTerm(),
                        gateway.getFlashcard(flashcardId).getDefinition(), flashcardId, true));
            }
        }
        LocalDateTime creationDate = LocalDateTime.now();
        int flashcardId = gateway.saveFlashcard(new FlashcardDsRequestModel(term, definition, creationDate, -1,
                requestModel.getFlashcardSetId()));
        return presenter.prepareSuccessView(new
                CreateFlashcardResponseModel(creationDate, term, definition, flashcardId, false));

    }


    /**
     * Try to create a flashcard with the given term and definition in the given flashcard set,
     * overwrite the flashcard at flashcardId.
     * If the term or definition is empty flashcard set does not exist, return the corresponding error.
     * If there is already a flashcard with the same term, return the response model representing the existing flashcard.
     * @param requestModel request model
     * @param flashcardId flashcardId for the method to overwrite
     * @return success or failure view
     */
    @Override
    public CreateFlashcardResponseModel create(CreateFlashcardRequestModel requestModel, int flashcardId){
        String term = requestModel.getTerm().replace("\n", " ").trim();
        String definition = requestModel.getDefinition().replace("\n", " ").trim();
        if(term.isEmpty() || definition.isEmpty()){
            return presenter.prepareFailView("Term or definition is empty.");
        }
        else if (gateway.getFlashcardSet(requestModel.getFlashcardSetId()) == null) {
            return presenter.prepareFailView("Flashcard set does not exist.");
        }
        LocalDateTime creationDate = LocalDateTime.now();
        gateway.editFlashcard(new FlashcardDsRequestModel(term, definition, creationDate, flashcardId,
                requestModel.getFlashcardSetId()));
        return new CreateFlashcardResponseModel(creationDate, term, definition, flashcardId,false);
    }
}
