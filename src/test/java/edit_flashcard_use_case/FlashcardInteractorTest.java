package edit_flashcard_use_case;
import edit_flashcard_use_case.screens.InMemoryFlashcard;
import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.IFlashcardDataAccess;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class FlashcardInteractorTest {

    @Test
    public void editSuccess(){
        IFlashcardDataAccess fcDataAccess = new InMemoryFlashcard();
        DBGateway dbGateway = new DBGateway(fcDataAccess, null, null);
        EditFlashcardOutputBoundary presenter = new EditFlashcardOutputBoundary(){

            @Override
            public EditFlashcardResponseModel prepareSuccessView(EditFlashcardResponseModel flashcard) {
                Assertions.assertEquals("table", flashcard.getTermEdit());
                Assertions.assertEquals("furniture to work on", flashcard.getDefinitionEdit());

                FlashcardDsRequestModel newFlashcard = fcDataAccess.getFlashcard(flashcard.getFlashcardId());
                Assertions.assertEquals(newFlashcard.getTerm(), flashcard.getTermEdit());
                Assertions.assertEquals(newFlashcard.getDefinition(), flashcard.getDefinitionEdit());
                return null;
            }

            @Override
            public EditFlashcardResponseModel prepareFailView(String error) {
                Assertions.fail("Failure is unexpected");
                return null;
            }
        };

        EditFlashcardInputBoundary interactor = new EditFlashcardInteractor(dbGateway, presenter);

        int flashcardId = 1;
        FlashcardDsRequestModel currentFlashcard = new FlashcardDsRequestModel("chair", "furniture to sit on", LocalDateTime.now(), flashcardId, 0);
        fcDataAccess.saveFlashcard(currentFlashcard);
        EditFlashcardRequestModel inputData = new EditFlashcardRequestModel(flashcardId, "table", "furniture to work on");

        interactor.edit(inputData);

    }

    @Test
    public void editSuccessEmptyDefinition(){
        IFlashcardDataAccess fcDataAccess = new InMemoryFlashcard();
        DBGateway dbGateway = new DBGateway(fcDataAccess, null, null);
        EditFlashcardOutputBoundary presenter = new EditFlashcardOutputBoundary(){

            @Override
            public EditFlashcardResponseModel prepareSuccessView(EditFlashcardResponseModel flashcard) {
                Assertions.assertEquals("table", flashcard.getTermEdit());
                Assertions.assertEquals("", flashcard.getDefinitionEdit());

                FlashcardDsRequestModel newFlashcard = fcDataAccess.getFlashcard(flashcard.getFlashcardId());
                Assertions.assertEquals(newFlashcard.getTerm(), flashcard.getTermEdit());
                Assertions.assertEquals(newFlashcard.getDefinition(), flashcard.getDefinitionEdit());
                return null;
            }

            @Override
            public EditFlashcardResponseModel prepareFailView(String error) {
                Assertions.fail("Failure is unexpected");
                return null;
            }
        };

        EditFlashcardInputBoundary interactor = new EditFlashcardInteractor(dbGateway, presenter);

        int flashcardId = 1;
        FlashcardDsRequestModel currentFlashcard = new FlashcardDsRequestModel("chair", "furniture to sit on", LocalDateTime.now(), flashcardId, 0);
        fcDataAccess.saveFlashcard(currentFlashcard);
        EditFlashcardRequestModel inputData = new EditFlashcardRequestModel(flashcardId, "table", "");

        interactor.edit(inputData);

    }

    @Test
    public void editFailEmptyTerm(){
        IFlashcardDataAccess fcDataAccess = new InMemoryFlashcard();
        DBGateway dbGateway = new DBGateway(fcDataAccess, null, null);
        EditFlashcardOutputBoundary presenter = new EditFlashcardOutputBoundary(){

            @Override
            public EditFlashcardResponseModel prepareSuccessView(EditFlashcardResponseModel flashcard) {
                Assertions.fail("Success is unexpected");
                return null;
            }

            @Override
            public EditFlashcardResponseModel prepareFailView(String error) {
                Assertions.assertEquals("Error: Term input cannot be empty.", error);
                return null;
            }
        };

        EditFlashcardInputBoundary interactor = new EditFlashcardInteractor(dbGateway, presenter);

        int flashcardId = 1;
        FlashcardDsRequestModel currentFlashcard = new FlashcardDsRequestModel("chair", "furniture to sit on", LocalDateTime.now(), flashcardId, 0);
        fcDataAccess.saveFlashcard(currentFlashcard);
        EditFlashcardRequestModel inputData = new EditFlashcardRequestModel(flashcardId, "", "furniture to work on");

        interactor.edit(inputData);

        //Check to see that nothing was changed when inputting an empty string as a Term.
        FlashcardDsRequestModel newFlashcard = fcDataAccess.getFlashcard(flashcardId);
        Assertions.assertEquals("chair", newFlashcard.getTerm());
        Assertions.assertEquals("furniture to sit on", newFlashcard.getDefinition());

    }

    @Test
    public void editFailEmptyTermEmptyDefinition(){
        IFlashcardDataAccess fcDataAccess = new InMemoryFlashcard();
        DBGateway dbGateway = new DBGateway(fcDataAccess, null, null);
        EditFlashcardOutputBoundary presenter = new EditFlashcardOutputBoundary(){

            @Override
            public EditFlashcardResponseModel prepareSuccessView(EditFlashcardResponseModel flashcard) {
                Assertions.fail("Success is unexpected");
                return null;
            }

            @Override
            public EditFlashcardResponseModel prepareFailView(String error) {
                Assertions.assertEquals("Error: Term input cannot be empty.", error);
                return null;
            }
        };

        EditFlashcardInputBoundary interactor = new EditFlashcardInteractor(dbGateway, presenter);

        int flashcardId = 1;
        FlashcardDsRequestModel currentFlashcard = new FlashcardDsRequestModel("chair", "furniture to sit on", LocalDateTime.now(), flashcardId, 0);
        fcDataAccess.saveFlashcard(currentFlashcard);
        EditFlashcardRequestModel inputData = new EditFlashcardRequestModel(flashcardId, "", "");

        interactor.edit(inputData);

        //Check to see that nothing was changed when inputting an empty string as a Term.
        FlashcardDsRequestModel newFlashcard = fcDataAccess.getFlashcard(flashcardId);
        Assertions.assertEquals("chair", newFlashcard.getTerm());
        Assertions.assertEquals("furniture to sit on", newFlashcard.getDefinition());

    }

}