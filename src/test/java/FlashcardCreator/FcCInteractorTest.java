package FlashcardCreator;

import dataAccess.DBGateway;
import dataAccess.IFlashcardDataAccess;
import dataAccess.IFlashcardSetDataAccess;
import entityRequestModels.FlashcardSetDsRequestModel;
import flashcardCreator.*;
import flashcardCreator.fcCScreens.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FcCInteractorTest {

    @Test
    public void create_success(){
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));

        FcCOutputBoundary presenter = new FcCOutputBoundary(){
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

            @Override
            public FcCResponseModel prepareFailView(String error){
                Assertions.fail("Unexpected Failure.");
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        interactor.create(new FcCRequestModel(0, "term", "definition"));
    }

    @Test
    public void create_failure_empty_term(){
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));

        FcCOutputBoundary presenter = new FcCOutputBoundary(){
            @Override
            public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel){
                Assertions.fail("Unexpected success");
                return null;
            }

            @Override
            public FcCResponseModel prepareFailView(String error){
                Assertions.assertEquals("Term or definition is empty.", error);
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        interactor.create(new FcCRequestModel(0, "", "definition"));
    }

    @Test
    public void create_failure_empty_definition(){
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));

        FcCOutputBoundary presenter = new FcCOutputBoundary(){
            @Override
            public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel){
                Assertions.fail("Unexpected success");
                return null;
            }

            @Override
            public FcCResponseModel prepareFailView(String error){
                Assertions.assertEquals("Term or definition is empty.", error);
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        interactor.create(new FcCRequestModel(0, "term", ""));
    }

    @Test
    public void create_failure_no_flashcard_set(){
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);

        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("test set","description",
                true, 0, "user", new ArrayList<>()));

        FcCOutputBoundary presenter = new FcCOutputBoundary(){
            @Override
            public FcCResponseModel prepareSuccessView(FcCResponseModel responseModel){
                Assertions.fail("Unexpected success");
                return null;
            }

            @Override
            public FcCResponseModel prepareFailView(String error){
                Assertions.assertEquals("Flashcard set does not exist.", error);
                return null;
            }
        };
        FcCInputBoundary interactor = new FcCInteractor(gateway, presenter);
        interactor.create(new FcCRequestModel(-1, "term", "definition"));
    }
}
