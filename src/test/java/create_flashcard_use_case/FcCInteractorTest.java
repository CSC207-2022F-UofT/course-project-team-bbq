package create_flashcard_use_case;

import data_access.DBGateway;
import data_access.IFlashcardDataAccess;
import data_access.IFlashcardSetDataAccess;
import data_access.entity_request_models.FlashcardSetDsRequestModel;
import create_flashcard_use_case.fcCScreens.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for create_flashcard_use_case.FcCInteractor
 * @author Junyu Chen
 */
public class FcCInteractorTest {

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
        FcCOutputBoundary presenter = new FcCOutputBoundary(){
            //Check if the flashcard is saved when response from interactor is successful.
            @Override
            public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel){
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
            public FcCResponseModel prepareFailView(String error){
                Assertions.fail("Unexpected Failure.");
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        //Valid input for flashcard.
        interactor.create(new FcCRequestModel(0, "term", "definition"));
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
        FcCOutputBoundary presenter = new FcCOutputBoundary(){
            //Success should not happen with this input.
            @Override
            public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel){
                Assertions.fail("Unexpected success");
                return null;
            }

            ////Check if error thrown is correct
            @Override
            public FcCResponseModel prepareFailView(String error){
                Assertions.assertEquals("Term or definition is empty.", error);
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        //Input with empty term.
        interactor.create(new FcCRequestModel(0, "", "definition"));
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
        FcCOutputBoundary presenter = new FcCOutputBoundary(){
            //Success should not happen.
            @Override
            public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel){
                Assertions.fail("Unexpected success");
                return null;
            }

            //Check if error thrown is correct
            @Override
            public FcCResponseModel prepareFailView(String error){
                Assertions.assertEquals("Term or definition is empty.", error);
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        //Input with empty definition.
        interactor.create(new FcCRequestModel(0, "term", ""));
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
        FcCOutputBoundary presenter = new FcCOutputBoundary() {
            //Success should not happen.
            @Override
            public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel) {
                Assertions.fail("Unexpected success");
                return null;
            }

            //Check if error thrown is correct.
            @Override
            public FcCResponseModel prepareFailView(String error) {
                Assertions.assertEquals("Flashcard set does not exist.", error);
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        //Flashcard creation.
        interactor.create(new FcCRequestModel(0, "term", "definition"));
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
        FcCOutputBoundary presenter = new FcCOutputBoundary(){
            //Success should not happen.
            @Override
            public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel){
                Assertions.fail("Unexpected success");
                return null;
            }
            //Check if error thrown is correct.
            @Override
            public FcCResponseModel prepareFailView(String error){
                Assertions.assertEquals("Term or definition is empty.", error);
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        //Input with empty term and definition.
        interactor.create(new FcCRequestModel(-1, "", ""));
    }
    /**
     * Test for creation of valid flashcard with multiple lines.
     */
    @Test
    public void create_success_multiple_lines_input(){
        //Creating database for the test.
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        //Adding flash card set with id 0 for interactor to create flashcard in.
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));
        //Changed presenter for the test.
        FcCOutputBoundary presenter = new FcCOutputBoundary(){
            //Check if the flashcard is saved when response from interactor is successful.
            @Override
            public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel){
                Assertions.assertEquals(gateway.getFlashcard(0).getTerm(), "term term");
                Assertions.assertEquals(gateway.getFlashcard(0).getDefinition(), "definition definition");
                List<Integer> ids = new ArrayList<>();
                ids.add(0);
                Assertions.assertEquals(gateway.getFlashcardSet(0).getFlashcardIds(),
                        ids);
                return null;
            }

            //Errors should not happen.
            @Override
            public FcCResponseModel prepareFailView(String error){
                Assertions.fail("Unexpected Failure.");
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        //Input with multiple lines.
        interactor.create(new FcCRequestModel(0, "term\nterm", "definition\ndefinition"));
    }
}
