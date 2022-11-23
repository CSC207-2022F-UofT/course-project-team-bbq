package edit_flashcardset;

import dataAccess.DBGateway;
import dataAccess.IFlashcardSetDataAccess;
import entityRequestModels.FlashcardSetDsRequestModel;

import java.util.List;

public class FCSetEditorInteractor implements FCSetEditorInputBoundary {
    private final FCSetEditorOutputBoundary presenter;
    private final IFlashcardSetDataAccess fcSetGateway;

    public FCSetEditorInteractor(DBGateway dbGateway, FCSetEditorOutputBoundary presenter){
        this.presenter = presenter;
        fcSetGateway = dbGateway.getFlashcardSetGateway();
    }

    @Override
    public FCSetEditorResponseModel edit(FCSetEditorRequestModel requestModel) {
        String titleEdit = requestModel.getTitleEdit();
        String descriptionEdit = requestModel.getDescriptionEdit();

        if(titleEdit.equals("")){
            return presenter.prepareFailView("Error: Title cannot be empty.");
        }
        else{
            int fcSetId = requestModel.getFlashcardSetId();
            FlashcardSetDsRequestModel oldFcSet = fcSetGateway.getFlashcardSet(fcSetId);
            boolean isPrivate = oldFcSet.getIsPrivate();
            String ownerUsername = oldFcSet.getOwnerUsername();
            List<Integer> flashcardIds = oldFcSet.getFlashcardIds();
            FlashcardSetDsRequestModel newFcSet = new FlashcardSetDsRequestModel(
                    titleEdit, descriptionEdit, isPrivate, fcSetId,ownerUsername, flashcardIds);
            fcSetGateway.editTitleAndDescription(newFcSet);

            FCSetEditorResponseModel responseModel = new FCSetEditorResponseModel(fcSetId, titleEdit, descriptionEdit);
            return presenter.prepareSuccessView(responseModel);
        }
    }
}