package edit_flashcard_set_use_case;

import data_access.DBGateway;
import data_access.entity_request_models.FlashcardSetDsRequestModel;

import java.util.List;

/**
 * Flashcard set editor interactor.
 * Application business rules (use case layer).
 * @author Thomas Shim
 */
public class FCSetEditorInteractor implements FCSetEditorInputBoundary {
    private final DBGateway dbGateway;
    private final FCSetEditorOutputBoundary fcSetEditorOB;

    /**
     * Creates a new FCSetEditorInteractor object.
     * @param dbGateway Database gateway that gives access to database.
     * @param fcSetEditorOB A FCSetEditorOutputBoundary that prepares the view according to the given input.
     */
    public FCSetEditorInteractor(DBGateway dbGateway, FCSetEditorOutputBoundary fcSetEditorOB){
        this.dbGateway = dbGateway;
        this.fcSetEditorOB = fcSetEditorOB;
    }

    /**
     * Edits the flashcard set according to the edits given and returns a success or fail view accordingly. This method
     * checks if the given edited title is empty or not. If empty we return a fail view via the output boundary. If not
     * empty we return a success view that returns the new edited title and description in a
     * FCSetEditorResponseModel via the output boundary.
     * @param requestModel the user's input in form of FCSetEditorRequestModel.
     * @return FCSetEditorResponseModel which is in the form of a fail view or a success view.
     */
    @Override
    public FCSetEditorResponseModel edit(FCSetEditorRequestModel requestModel) {
        String titleEdit = requestModel.getTitleEdit();
        String descriptionEdit = requestModel.getDescriptionEdit();

        //Check if title input is empty.
        //If empty we return fail view.
        if(titleEdit.equals("")){
            return fcSetEditorOB.prepareFailView("Error: Title cannot be empty.");
        }
        //If not empty, we edit the flashcard set via the dbGateway, and then we return success view.
        else{
            int fcSetId = requestModel.getFlashcardSetId();
            FlashcardSetDsRequestModel oldFcSet = dbGateway.getFlashcardSet(fcSetId);
            boolean isPrivate = oldFcSet.getIsPrivate();
            String ownerUsername = oldFcSet.getOwnerUsername();
            List<Integer> flashcardIds = oldFcSet.getFlashcardIds();
            FlashcardSetDsRequestModel newFcSet = new FlashcardSetDsRequestModel(
                    titleEdit, descriptionEdit, isPrivate, fcSetId,ownerUsername, flashcardIds);
            dbGateway.editFlashcardSet(newFcSet);

            FCSetEditorResponseModel responseModel = new FCSetEditorResponseModel(fcSetId, titleEdit, descriptionEdit);
            return fcSetEditorOB.prepareSuccessView(responseModel);
        }
    }
}