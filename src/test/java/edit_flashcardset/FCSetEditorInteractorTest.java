package edit_flashcardset;
import edit_flashcardset.screens.InMemoryFlashcardSet;
import dataAccess.DBGateway;
import dataAccess.IFlashcardSetDataAccess;
import entityRequestModels.FlashcardSetDsRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class FCSetEditorInteractorTest {

    @Test
    public void editSuccess(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        FCSetEditorOutputBoundary presenter = new FCSetEditorOutputBoundary(){


            @Override
            public FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel flashcardSet) {
                Assertions.assertEquals("csc236", flashcardSet.getTitleEdit());
                Assertions.assertEquals("Theory of Computation", flashcardSet.getDescriptionEdit());

                FlashcardSetDsRequestModel newFlashcardSet = fcSetDataAccess.getFlashcardSet(flashcardSet.getFlashcardSetId());
                Assertions.assertEquals(flashcardSet.getTitleEdit(), newFlashcardSet.getTitle());
                Assertions.assertEquals(flashcardSet.getDescriptionEdit(), newFlashcardSet.getDescription());
                return null;
            }

            @Override
            public FCSetEditorResponseModel prepareFailView(String error) {
                Assertions.fail("Failure is unexpected.");
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
    public void editSuccessEmptyDescription(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        FCSetEditorOutputBoundary presenter = new FCSetEditorOutputBoundary(){


            @Override
            public FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel flashcardSet) {
                Assertions.assertEquals("csc236", flashcardSet.getTitleEdit());
                Assertions.assertEquals("", flashcardSet.getDescriptionEdit());

                FlashcardSetDsRequestModel newFlashcardSet = fcSetDataAccess.getFlashcardSet(flashcardSet.getFlashcardSetId());
                Assertions.assertEquals(flashcardSet.getTitleEdit(), newFlashcardSet.getTitle());
                Assertions.assertEquals(flashcardSet.getDescriptionEdit(), newFlashcardSet.getDescription());
                return null;
            }

            @Override
            public FCSetEditorResponseModel prepareFailView(String error) {
                Assertions.fail("Failure is unexpected.");
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

        FCSetEditorRequestModel inputData = new FCSetEditorRequestModel(flashcardSetId, "csc236", "");

        interactor.edit(inputData);

    }

    @Test
    public void editFailEmptyTitle(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        FCSetEditorOutputBoundary presenter = new FCSetEditorOutputBoundary(){


            @Override
            public FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel flashcardSet) {
                Assertions.fail("Success not expected.");
                return null;
            }

            @Override
            public FCSetEditorResponseModel prepareFailView(String error) {
                Assertions.assertEquals("Error: Title cannot be empty.", error);
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
        Assertions.assertEquals("csc207", newFlashcard.getTitle());
        Assertions.assertEquals("software Design", newFlashcard.getDescription());

    }

    @Test
    public void editFailEmptyTitleEmptyDescription(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        FCSetEditorOutputBoundary presenter = new FCSetEditorOutputBoundary(){


            @Override
            public FCSetEditorResponseModel prepareSuccessView(FCSetEditorResponseModel flashcardSet) {
                Assertions.fail("Success not expected.");
                return null;
            }

            @Override
            public FCSetEditorResponseModel prepareFailView(String error) {
                Assertions.assertEquals("Error: Title cannot be empty.", error);
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

        FCSetEditorRequestModel inputData = new FCSetEditorRequestModel(flashcardSetId, "", "");

        interactor.edit(inputData);

        FlashcardSetDsRequestModel newFlashcard = fcSetDataAccess.getFlashcardSet(flashcardSetId);
        Assertions.assertEquals("csc207", newFlashcard.getTitle());
        Assertions.assertEquals("software Design", newFlashcard.getDescription());

    }
}
