package create_flashcard_use_case;

import create_flashcard_use_case.function_testing.InMemoryFlashcard;
import create_flashcard_use_case.function_testing.InMemoryFlashcardSet;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.IFlashcardDataAccess;
import data_access_use_case.IFlashcardSetDataAccess;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests for create_flashcard_use_case.FcCInteractor
 * @author Junyu Chen
 */
public class CreateFlashcardUseCaseTest {

    /**
     * Test for creation of valid flashcard.
     */
    @Test
    public void create_success(){
        //Creating database for the test.
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        //Adding flash card set with id 0 for interactor to create flashcard in.
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));
        //Changed presenter for the test.
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardOutputBoundary(){
            //Check if the flashcard is saved when response from interactor is successful.
            @Override
            public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel){
                Assertions.assertEquals(gateway.getFlashcard(0).getTerm(), "term");
                Assertions.assertEquals(gateway.getFlashcard(0).getDefinition(), "definition");
                List<Integer> ids = new ArrayList<>();
                ids.add(0);
                Assertions.assertEquals(gateway.getFlashcardSet(0).getFlashcardIds(),
                        ids);
                return null;
            }

            //Errors should not happen.
            @Override
            public CreateFlashcardResponseModel prepareFailView(String error){
                Assertions.fail("Unexpected Failure.");
                return null;
            }

            //Duplicates should not exist.
            @Override
            public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected Duplicate.");
                return null;
            }
        };
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway, presenter);
        //Valid input for flashcard.
        interactor.create(new CreateFlashcardRequestModel(0, "term", "definition"));
    }
    /**
     * Test for creation of valid flashcard with term of multiple lines.
     */
    @Test
    public void create_success_multiple_lines_term(){
        //Creating database for the test.
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        //Adding flash card set with id 0 for interactor to create flashcard in.
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));
        //Changed presenter for the test.
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardOutputBoundary(){
            //Check if the flashcard is saved when response from interactor is successful.
            @Override
            public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel){
                Assertions.assertEquals(gateway.getFlashcard(0).getTerm(), "term term");
                Assertions.assertEquals(gateway.getFlashcard(0).getDefinition(), "definition");
                List<Integer> ids = new ArrayList<>();
                ids.add(0);
                Assertions.assertEquals(gateway.getFlashcardSet(0).getFlashcardIds(),
                        ids);
                return null;
            }

            //Errors should not happen.
            @Override
            public CreateFlashcardResponseModel prepareFailView(String error){
                Assertions.fail("Unexpected Failure.");
                return null;
            }

            //Duplicates should not exist.
            @Override
            public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected Duplicate.");
                return null;
            }
        };
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway, presenter);
        //Input with multiple lines.
        interactor.create(new CreateFlashcardRequestModel(0, "term\nterm", "definition"));
    }
    /**
     * Test for creation of valid flashcard with definition of multiple lines.
     */
    @Test
    public void create_success_multiple_lines_definition(){
        //Creating database for the test.
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        //Adding flash card set with id 0 for interactor to create flashcard in.
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));
        //Changed presenter for the test.
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardOutputBoundary(){
            //Check if the flashcard is saved when response from interactor is successful.
            @Override
            public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel){
                Assertions.assertEquals(gateway.getFlashcard(0).getTerm(), "term");
                Assertions.assertEquals(gateway.getFlashcard(0).getDefinition(),
                        "definition definition");
                List<Integer> ids = new ArrayList<>();
                ids.add(0);
                Assertions.assertEquals(gateway.getFlashcardSet(0).getFlashcardIds(),
                        ids);
                return null;
            }

            //Errors should not happen.
            @Override
            public CreateFlashcardResponseModel prepareFailView(String error){
                Assertions.fail("Unexpected Failure.");
                return null;
            }

            //Duplicates should not exist.
            @Override
            public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected Duplicate.");
                return null;
            }
        };
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway, presenter);
        //Input with multiple lines.
        interactor.create(new CreateFlashcardRequestModel(0, "term", "definition\ndefinition"));
    }

    /**
     * Test for overwrite flashcard.
     */
    @Test
    public void create_success_overwrite_flashcard() {
        //Creating database for the test.
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        //Adding flash card set with id 0 for interactor to create flashcard in.
        List<Integer> flashcardIds = new ArrayList<>();
        flashcardIds.add(0);
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set", "description",
                true, 0, "user", flashcardIds));
        //Adding flashcard.
        flashcardDataAccess.saveFlashcard(new FlashcardDsRequestModel("term", "definition",
                LocalDateTime.now(), -1, 0));
        //Changed presenter for the test.
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardOutputBoundary() {
            //Success should not happen.
            @Override
            public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel) {
                Assertions.assertEquals(gateway.getFlashcard(0).getTerm(), "term");
                Assertions.assertEquals(gateway.getFlashcard(0).getDefinition(),
                        "definition definition");
                List<Integer> ids = new ArrayList<>();
                ids.add(0);
                Assertions.assertEquals(gateway.getFlashcardSet(0).getFlashcardIds(),
                        ids);
                return null;
            }

            //Errors should not happen.
            @Override
            public CreateFlashcardResponseModel prepareFailView(String error) {
                Assertions.fail("Unexpected Failure.");
                return null;
            }

            //Duplicate should not exist anymore.
            @Override
            public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected Duplicate.");
                return null;
            }
        };
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway, presenter);
        //Input with multiple lines.
        interactor.create(new CreateFlashcardRequestModel(0, "term", "definition\ndefinition"), 0);
    }

    /**
     * Test for Creation of flashcard with empty term.
     */
    @Test
    public void create_failure_empty_term(){
        //Creating database for Flashcard
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        //Adding flash card set with id 0 for interactor to create flashcard in.
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));

        //Changed presenter for the test.
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardOutputBoundary(){
            //Success should not happen with this input.
            @Override
            public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel){
                Assertions.fail("Unexpected success");
                return null;
            }

            //Check if error thrown is correct
            @Override
            public CreateFlashcardResponseModel prepareFailView(String error){
                Assertions.assertEquals("Term or definition is empty.", error);
                return null;
            }

            //Duplicates should not exist.
            @Override
            public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected Duplicate.");
                return null;
            }
        };
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway, presenter);
        //Input with empty term.
        interactor.create(new CreateFlashcardRequestModel(0, "", "definition"));
    }

    /**
     * Test for Creation of flashcard with empty definition.
     */
    @Test
    public void create_failure_empty_definition(){
        //Creating data base
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        //Adding test flashcard set.
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));

        //Creating new response for the test.
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardOutputBoundary(){
            //Success should not happen.
            @Override
            public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel){
                Assertions.fail("Unexpected success");
                return null;
            }

            //Check if error thrown is correct
            @Override
            public CreateFlashcardResponseModel prepareFailView(String error){
                Assertions.assertEquals("Term or definition is empty.", error);
                return null;
            }

            //Duplicates should not exist.
            @Override
            public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected Duplicate.");
                return null;
            }
        };
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway, presenter);
        //Input with empty definition.
        interactor.create(new CreateFlashcardRequestModel(0, "term", ""));
    }

    /**
     * Test for Creation of flashcard with non-existing flashcard set.
     */
    @Test
    public void create_failure_no_flashcard_set() {
        //Creating database
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        //No testing flashcard set.

        //Creating presenter for test.
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardOutputBoundary() {
            //Success should not happen.
            @Override
            public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected success");
                return null;
            }

            //Check if error thrown is correct.
            @Override
            public CreateFlashcardResponseModel prepareFailView(String error) {
                Assertions.assertEquals("Flashcard set does not exist.", error);
                return null;
            }

            //Duplicates should not exist.
            @Override
            public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected Duplicate.");
                return null;
            }
        };
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway, presenter);
        //Flashcard creation.
        interactor.create(new CreateFlashcardRequestModel(0, "term", "definition"));
    }

    /**
     * Test for Creation of flashcard with empty term and definition.
     */
    @Test
    public void create_failure_empty_term_and_definition(){
        //Creating database
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        //Adding testing flashcard set.
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));

        //Creating presenter for test.
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardOutputBoundary(){
            //Success should not happen.
            @Override
            public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel){
                Assertions.fail("Unexpected success");
                return null;
            }
            //Check if error thrown is correct.
            @Override
            public CreateFlashcardResponseModel prepareFailView(String error){
                Assertions.assertEquals("Term or definition is empty.", error);
                return null;
            }

            //Duplicates should not exist.
            @Override
            public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected Duplicate.");
                return null;
            }
        };
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway, presenter);
        //Input with empty term and definition.
        interactor.create(new CreateFlashcardRequestModel(-1, "", ""));
    }

    /**
     * Test for detecting duplicate term.
     */
    @Test
    public void create_failure_duplicate() {
        //Creating database for the test.
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        //Adding flash card set with id 0 for interactor to create flashcard in.
        List<Integer> flashcardIds = new ArrayList<>();
        flashcardIds.add(0);
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set", "description",
                true, 0, "user", flashcardIds));
        //Adding flashcard.
        flashcardDataAccess.saveFlashcard(new FlashcardDsRequestModel("term", "definition",
                LocalDateTime.now(), -1, 0));
        //Changed presenter for the test.
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardOutputBoundary() {
            //Success should not happen.
            @Override
            public CreateFlashcardResponseModel prepareSuccessView(CreateFlashcardResponseModel responseModel) {
                Assertions.fail("Unexpected Success.");
                return null;
            }

            //Errors should not happen.
            @Override
            public CreateFlashcardResponseModel prepareFailView(String error) {
                Assertions.fail("Unexpected Failure.");
                return null;
            }

            //Check if duplicate is returned correctly.
            @Override
            public CreateFlashcardResponseModel prepareDuplicateView(CreateFlashcardResponseModel responseModel) {
                Assertions.assertEquals(responseModel.getTerm(), "term");
                Assertions.assertEquals(responseModel.getDefinition(), "definition");
                Assertions.assertEquals(responseModel.getFlashcardId(), 0);
                Assertions.assertEquals(responseModel.existsDuplicate(), true);
                return null;
            }
        };
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway, presenter);
        //Input with multiple lines.
        interactor.create(new CreateFlashcardRequestModel(0, "term", "definition\ndefinition"));
    }
}
