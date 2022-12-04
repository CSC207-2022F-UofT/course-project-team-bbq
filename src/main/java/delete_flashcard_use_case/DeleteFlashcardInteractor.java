package delete_flashcard_use_case;

import frameworks_and_drivers.database.DBGateway;

import java.time.LocalDateTime;
/**
 * Interactor for flashcard removal.
 * Application business rules.
 * @author Junyu Chen
 */

public class DeleteFlashcardInteractor implements DeleteFlashcardInputBoundary {
    final DeleteFlashcardOutputBoundary presenter;
    final DBGateway gateway;

    /**
     * Create DeleteFlashcardInteractor.
     * @param gateway Database gateway.
     * @param presenter presenter that prepares success or failure view.
     */
    public DeleteFlashcardInteractor(DBGateway gateway, DeleteFlashcardOutputBoundary presenter){
        this.presenter = presenter;
        this.gateway = gateway;
    }

    /**
     * Delete flashcard from flashcard set
     * @param requestModel Request model that give the flashcard set and flashcard id.
     * @return Success or failure view.
     */
    @Override
    public DeleteFlashcardResponseModel delete(DeleteFlashcardRequestModel requestModel) {
        String term ;
        String cardSetTitle;
        try{
            term = gateway.getFlashcard(requestModel.getFlashcardId()).getTerm();
        }catch (NullPointerException e){
            return presenter.prepareFailView("Flashcard does not exist.");
        }
        try{
            cardSetTitle = gateway.getFlashcardSet(requestModel.getFlashcardSetId()).getTitle();
        }catch (NullPointerException e){
            return presenter.prepareFailView("Flashcard set does not exist.");
        }
        if(!gateway.getFlashcardSet(requestModel.getFlashcardSetId()).getFlashcardIds().
                contains(requestModel.getFlashcardId())){
            return presenter.prepareFailView("Flashcard not in this flashcard set.");
        }
        gateway.deleteFlashcard(requestModel.getFlashcardSetId(), requestModel.getFlashcardId());
        return presenter.prepareSuccessView(new DeleteFlashcardResponseModel(LocalDateTime.now(), term, cardSetTitle));
    }
}
