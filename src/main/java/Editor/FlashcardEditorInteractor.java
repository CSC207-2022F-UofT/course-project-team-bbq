package Editor;

import dataAccess.DBGateway;
import dataAccess.IFlashcardDataAccess;
import entityRequestModels.FlashcardDsRequestModel;

import java.time.LocalDateTime;

public class FlashcardEditorInteractor implements FlashcardEditorInputBoundary {

    final DBGateway dbGateway;
    final FlashcardEditorOutputBoundary fcEditorOB;
    final IFlashcardDataAccess fcDBGateway;

    public FlashcardEditorInteractor(DBGateway dbGateway, FlashcardEditorOutputBoundary fcEditorOB){
        this.dbGateway = dbGateway;
        this.fcEditorOB = fcEditorOB;
        this.fcDBGateway = dbGateway.getFlashcardGateway();
    }

    @Override
    public FlashcardEditorResponseModel edit(FlashcardEditorRequestModel requestModel) {
        String term = requestModel.getTermEdit();
        String definition = requestModel.getDefinitionEdit();
        int flashcardId = requestModel.getFlashcardId();

        if (term.equals("")){
            return fcEditorOB.prepareFailView("Term input is empty. Term input cannot be empty.");
        }
        else{
            FlashcardDsRequestModel oldFlashcard = fcDBGateway.getFlashcard(flashcardId);
            LocalDateTime creationDate = oldFlashcard.getCreationDate();
            int belongsToId = oldFlashcard.getBelongsToId();
            FlashcardDsRequestModel newFlashcard = new FlashcardDsRequestModel(term, definition, creationDate, belongsToId, flashcardId);

            fcDBGateway.editFlashcard(newFlashcard);

            FlashcardEditorResponseModel responseModel = new FlashcardEditorResponseModel(term, definition, flashcardId);
            return fcEditorOB.prepareSuccessView(responseModel);
        }
    }
}
