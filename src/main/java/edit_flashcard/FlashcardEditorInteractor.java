package edit_flashcard;

import dataAccess.DBGateway;
import entityRequestModels.FlashcardDsRequestModel;
import java.time.LocalDateTime;

/**
 * Flashcard editor interactor.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public class FlashcardEditorInteractor implements FlashcardEditorInputBoundary {

    private final DBGateway dbGateway;
    private final FlashcardEditorOutputBoundary fcEditorOB;

    /**
     * Creates a new FlashcardEditorInteractor object.
     * @param dbGateway Database gateway that gives access to database.
     * @param fcEditorOB A FlashcardEditorOutputBoundary that prepares the view according to the given input.
     */
    public FlashcardEditorInteractor(DBGateway dbGateway, FlashcardEditorOutputBoundary fcEditorOB){
        this.dbGateway = dbGateway;
        this.fcEditorOB = fcEditorOB;
    }

    /**
     * Edits the flashcard according to the edits given and returns a success or fail view accordingly. This method
     * checks if the given edited term is empty or not. If empty we return a fail view via the output boundary. If not
     * empty we return a success view that returns the new edited term and definition in a FlashcardEditorResponseModel
     * via the output boundary.
     * @param requestModel the user's input in form of FlashcardEditorRequestModel.
     * @return FlashcardEditorResponseModel which is in the form of a fail view or a success view.
     */
    @Override
    public FlashcardEditorResponseModel edit(FlashcardEditorRequestModel requestModel) {
        String termEdit = requestModel.getTermEdit();
        String definitionEdit = requestModel.getDefinitionEdit();
        int flashcardId = requestModel.getFlashcardId();

        //Check if term input is empty.
        //If empty we return fail view.
        if (termEdit.equals("")){
            return fcEditorOB.prepareFailView("Error: Term input cannot be empty.");
        }
        //If not empty, we edit the flashcard via the dbGateway, and then we return success view.
        else{
            FlashcardDsRequestModel oldFlashcard = dbGateway.getFlashcard(flashcardId);
            LocalDateTime creationDate = oldFlashcard.getCreationDate();
            int belongsToId = oldFlashcard.getBelongsToId();
            FlashcardDsRequestModel newFlashcard = new FlashcardDsRequestModel(termEdit, definitionEdit, creationDate, flashcardId, belongsToId);

            dbGateway.editFlashcard(newFlashcard);

            FlashcardEditorResponseModel responseModel = new FlashcardEditorResponseModel(flashcardId, termEdit, definitionEdit);
            return fcEditorOB.prepareSuccessView(responseModel);
        }
    }
}