package Editor.Flashcard;

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
        String termEdit = requestModel.getTermEdit();
        String definitionEdit = requestModel.getDefinitionEdit();
        int flashcardId = requestModel.getFlashcardId();

        if (termEdit.equals("")){
            return fcEditorOB.prepareFailView("Error: Term input cannot be empty.");
        }
        else{
            FlashcardDsRequestModel oldFlashcard = fcDBGateway.getFlashcard(flashcardId);
            LocalDateTime creationDate = oldFlashcard.getCreationDate();
            int belongsToId = oldFlashcard.getBelongsToId();
            FlashcardDsRequestModel newFlashcard = new FlashcardDsRequestModel(termEdit, definitionEdit, creationDate, flashcardId, belongsToId);

            fcDBGateway.editFlashcard(newFlashcard);

            FlashcardEditorResponseModel responseModel = new FlashcardEditorResponseModel(flashcardId, termEdit, definitionEdit);
            return fcEditorOB.prepareSuccessView(responseModel);
        }
    }
}