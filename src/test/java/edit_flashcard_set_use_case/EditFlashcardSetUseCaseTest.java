package edit_flashcard_set_use_case;
import edit_flashcard_set_use_case.function_testing.InMemoryFlashcardSet;
import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.IFlashcardSetDataAccess;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class EditFlashcardSetUseCaseTest {

    @Test
    public void editSuccess(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        EditFlashcardSetOutputBoundary presenter = new EditFlashcardSetOutputBoundary(){


            @Override
            public EditFlashcardSetResponseModel prepareSuccessView(EditFlashcardSetResponseModel flashcardSet) {
                Assertions.assertEquals("csc236", flashcardSet.getTitleEdit());
                Assertions.assertEquals("Theory of Computation", flashcardSet.getDescriptionEdit());

                FlashcardSetDsRequestModel newFlashcardSet = fcSetDataAccess.getFlashcardSet(flashcardSet.getFlashcardSetId());
                Assertions.assertEquals(flashcardSet.getTitleEdit(), newFlashcardSet.getTitle());
                Assertions.assertEquals(flashcardSet.getDescriptionEdit(), newFlashcardSet.getDescription());
                return null;
            }

            @Override
            public EditFlashcardSetResponseModel prepareFailView(String error) {
                Assertions.fail("Failure is unexpected.");
                return null;
            }
        };

        EditFlashcardSetInputBoundary interactor = new EditFlashcardSetInteractor(dbGateway, presenter);

        int flashcardSetId = 1;
        List<Integer> flashcardIds = new ArrayList<>();
        flashcardIds.add(1);
        flashcardIds.add(2);
        flashcardIds.add(3);
        flashcardIds.add(4);

        FlashcardSetDsRequestModel currentFlashcard = new FlashcardSetDsRequestModel("csc207", "software Design", false, flashcardSetId, "user", flashcardIds);
        fcSetDataAccess.saveFlashcardSet(currentFlashcard);

        EditFlashcardSetRequestModel inputData = new EditFlashcardSetRequestModel(flashcardSetId, "csc236", "Theory of Computation");

        interactor.edit(inputData);

    }

    @Test
    public void editSuccessEmptyDescription(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        EditFlashcardSetOutputBoundary presenter = new EditFlashcardSetOutputBoundary(){


            @Override
            public EditFlashcardSetResponseModel prepareSuccessView(EditFlashcardSetResponseModel flashcardSet) {
                Assertions.assertEquals("csc236", flashcardSet.getTitleEdit());
                Assertions.assertEquals("", flashcardSet.getDescriptionEdit());

                FlashcardSetDsRequestModel newFlashcardSet = fcSetDataAccess.getFlashcardSet(flashcardSet.getFlashcardSetId());
                Assertions.assertEquals(flashcardSet.getTitleEdit(), newFlashcardSet.getTitle());
                Assertions.assertEquals(flashcardSet.getDescriptionEdit(), newFlashcardSet.getDescription());
                return null;
            }

            @Override
            public EditFlashcardSetResponseModel prepareFailView(String error) {
                Assertions.fail("Failure is unexpected.");
                return null;
            }
        };

        EditFlashcardSetInputBoundary interactor = new EditFlashcardSetInteractor(dbGateway, presenter);

        int flashcardSetId = 1;
        List<Integer> flashcardIds = new ArrayList<>();
        flashcardIds.add(1);
        flashcardIds.add(2);
        flashcardIds.add(3);
        flashcardIds.add(4);

        FlashcardSetDsRequestModel currentFlashcard = new FlashcardSetDsRequestModel("csc207", "software Design", false, flashcardSetId, "user", flashcardIds);
        fcSetDataAccess.saveFlashcardSet(currentFlashcard);

        EditFlashcardSetRequestModel inputData = new EditFlashcardSetRequestModel(flashcardSetId, "csc236", "");

        interactor.edit(inputData);

    }

    @Test
    public void editFailEmptyTitle(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        EditFlashcardSetOutputBoundary presenter = new EditFlashcardSetOutputBoundary(){


            @Override
            public EditFlashcardSetResponseModel prepareSuccessView(EditFlashcardSetResponseModel flashcardSet) {
                Assertions.fail("Success not expected.");
                return null;
            }

            @Override
            public EditFlashcardSetResponseModel prepareFailView(String error) {
                Assertions.assertEquals("Error: Title cannot be empty.", error);
                return null;
            }
        };

        EditFlashcardSetInputBoundary interactor = new EditFlashcardSetInteractor(dbGateway, presenter);

        int flashcardSetId = 1;
        List<Integer> flashcardIds = new ArrayList<>();
        flashcardIds.add(1);
        flashcardIds.add(2);
        flashcardIds.add(3);
        flashcardIds.add(4);

        FlashcardSetDsRequestModel currentFlashcard = new FlashcardSetDsRequestModel("csc207", "software Design", false, flashcardSetId, "user", flashcardIds);
        fcSetDataAccess.saveFlashcardSet(currentFlashcard);

        EditFlashcardSetRequestModel inputData = new EditFlashcardSetRequestModel(flashcardSetId, "", "Theory of Computation");

        interactor.edit(inputData);

        FlashcardSetDsRequestModel newFlashcard = fcSetDataAccess.getFlashcardSet(flashcardSetId);
        Assertions.assertEquals("csc207", newFlashcard.getTitle());
        Assertions.assertEquals("software Design", newFlashcard.getDescription());

    }

    @Test
    public void editFailEmptyTitleEmptyDescription(){
        IFlashcardSetDataAccess fcSetDataAccess = new InMemoryFlashcardSet();
        DBGateway dbGateway = new DBGateway(null, fcSetDataAccess, null);
        EditFlashcardSetOutputBoundary presenter = new EditFlashcardSetOutputBoundary(){


            @Override
            public EditFlashcardSetResponseModel prepareSuccessView(EditFlashcardSetResponseModel flashcardSet) {
                Assertions.fail("Success not expected.");
                return null;
            }

            @Override
            public EditFlashcardSetResponseModel prepareFailView(String error) {
                Assertions.assertEquals("Error: Title cannot be empty.", error);
                return null;
            }
        };

        EditFlashcardSetInputBoundary interactor = new EditFlashcardSetInteractor(dbGateway, presenter);

        int flashcardSetId = 1;
        List<Integer> flashcardIds = new ArrayList<>();
        flashcardIds.add(1);
        flashcardIds.add(2);
        flashcardIds.add(3);
        flashcardIds.add(4);

        FlashcardSetDsRequestModel currentFlashcard = new FlashcardSetDsRequestModel("csc207", "software Design", false, flashcardSetId, "user", flashcardIds);
        fcSetDataAccess.saveFlashcardSet(currentFlashcard);

        EditFlashcardSetRequestModel inputData = new EditFlashcardSetRequestModel(flashcardSetId, "", "");

        interactor.edit(inputData);

        FlashcardSetDsRequestModel newFlashcard = fcSetDataAccess.getFlashcardSet(flashcardSetId);
        Assertions.assertEquals("csc207", newFlashcard.getTitle());
        Assertions.assertEquals("software Design", newFlashcard.getDescription());

    }
}
