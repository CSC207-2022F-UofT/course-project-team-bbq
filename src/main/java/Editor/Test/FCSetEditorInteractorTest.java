package Editor.Test;

import Editor.FlashcardSet.*;
import Editor.FlashcardSet.screens.InMemoryFlashcardSet;
import dataAccess.DBGateway;
import dataAccess.IFlashcardSetDataAccess;
import entityRequestModels.FlashcardSetDsRequestModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FCSetEditorInteractorTest {

    @Test
    public void editSuccess(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        FCSetEditorOutputBoundary presenter = new FCSetEditorOutputBoundary(){


            @Override
            public FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel flashcardSet) {
                assertEquals("csc236", flashcardSet.getTitleEdit());
                assertEquals("Theory of Computation", flashcardSet.getDescriptionEdit());

                FlashcardSetDsRequestModel newFlashcardSet = fcSetDataAccess.getFlashcardSet(flashcardSet.getFlashcardSetId());
                assertEquals(flashcardSet.getTitleEdit(), newFlashcardSet.getTitle());
                assertEquals(flashcardSet.getDescriptionEdit(), newFlashcardSet.getDescription());
                return null;
            }

            @Override
            public FCSetEditorResponseModel prepareFailView(String error) {
                fail("Failure is unexpected.");
                return null;
            }
        };

        FCSetEditorInputBoundary interactor = new FCSetEditorInteractor(dbGateway, presenter);

        int flashcardSetId = 1;
        List<Integer> flashcardIds = new ArrayList<>();
        flashcardIds.add(1);
        flashcardIds.add(2);
        flashcardIds.add(3);
        flashcardIds.add(4);

        FlashcardSetDsRequestModel currentFlashcard = new FlashcardSetDsRequestModel("csc207", "software Design", false, flashcardSetId, "user", flashcardIds);
        fcSetDataAccess.saveFlashcardSet(currentFlashcard);

        FCSetEditorRequestModel inputData = new FCSetEditorRequestModel(flashcardSetId, "csc236", "Theory of Computation");

        interactor.edit(inputData);

    }

    @Test
    public void editFail(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        FCSetEditorOutputBoundary presenter = new FCSetEditorOutputBoundary(){


            @Override
            public FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel flashcardSet) {
                fail("Success not expected.");
                return null;
            }

            @Override
            public FCSetEditorResponseModel prepareFailView(String error) {
                assertEquals("Error: Title cannot be empty.", error);
                return null;
            }
        };

        FCSetEditorInputBoundary interactor = new FCSetEditorInteractor(dbGateway, presenter);

        int flashcardSetId = 1;
        List<Integer> flashcardIds = new ArrayList<>();
        flashcardIds.add(1);
        flashcardIds.add(2);
        flashcardIds.add(3);
        flashcardIds.add(4);

        FlashcardSetDsRequestModel currentFlashcard = new FlashcardSetDsRequestModel("csc207", "software Design", false, flashcardSetId, "user", flashcardIds);
        fcSetDataAccess.saveFlashcardSet(currentFlashcard);

        FCSetEditorRequestModel inputData = new FCSetEditorRequestModel(flashcardSetId, "", "Theory of Computation");

        interactor.edit(inputData);

        FlashcardSetDsRequestModel newFlashcard = fcSetDataAccess.getFlashcardSet(flashcardSetId);
        assertEquals("csc207", newFlashcard.getTitle());
        assertEquals("software Design", newFlashcard.getDescription());

    }
}
