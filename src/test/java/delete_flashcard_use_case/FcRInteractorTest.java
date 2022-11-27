package delete_flashcard_use_case;

import data_access.DBGateway;
import data_access.IFlashcardDataAccess;
import data_access.IFlashcardSetDataAccess;
import data_access.entity_request_models.FlashcardDsRequestModel;
import data_access.entity_request_models.FlashcardSetDsRequestModel;
import create_flashcard_use_case.fcCScreens.InMemoryFlashcard;
import create_flashcard_use_case.fcCScreens.InMemoryFlashcardSet;
import delete_flashcard_use_case.FcRScreens.FcRResponsePresenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FcRInteractorTest {
    @Test
    public void delete_success(){
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("title",
                "description", true, 0,"User", new ArrayList<>()));
        gateway.saveFlashcard(new FlashcardDsRequestModel("term", "definition",
                LocalDateTime.now(), 0,0));
        FcROutputBoundary presenter = new FcRResponsePresenter(){
            @Override
            public FcRResponseModel prepareSuccessView(FcRResponseModel responseModel){
                Assertions.assertEquals(gateway.getFlashcardSet(0).getFlashcardIds(), new ArrayList<>());
                System.out.println(flashcardDataAccess.getFlashcard(0));
                Assertions.assertNull(gateway.getFlashcard(0));
                return null;
            }

            @Override
            public FcRResponseModel prepareFailView(String error){
                System.out.println(error);
                Assertions.fail("Unexpected Failure.");
                return null;
            }
        };
        FcRInputBoundary interactor = new FcRInteractor(gateway, presenter);
        interactor.delete(new FcRRequestModel(0,0));
    }

    @Test
    public void delete_failure_no_flashcard(){
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("title",
                "description", true, 0,"User", new ArrayList<>()));
        FcROutputBoundary presenter = new FcRResponsePresenter(){
            @Override
            public FcRResponseModel prepareSuccessView(FcRResponseModel responseModel){
                Assertions.fail("Unexpected success.");
                return null;
            }

            @Override
            public FcRResponseModel prepareFailView(String error){
                Assertions.assertEquals("Flashcard does not exist.", error);
                return null;
            }
        };
        FcRInputBoundary interactor = new FcRInteractor(gateway, presenter);
        interactor.delete(new FcRRequestModel(0,0));
    }

    @Test
    public void delete_failure_no_flashcard_set(){
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("title",
                "description", true, 1,"User", new ArrayList<>()));
        gateway.saveFlashcard(new FlashcardDsRequestModel("term", "definition",
                LocalDateTime.now(), 0,1));
        FcROutputBoundary presenter = new FcRResponsePresenter(){
            @Override
            public FcRResponseModel prepareSuccessView(FcRResponseModel responseModel){
                Assertions.fail("Unexpected success.");
                return null;
            }

            @Override
            public FcRResponseModel prepareFailView(String error){
                Assertions.assertEquals("Flashcard set does not exist.", error);
                return null;
            }
        };
        FcRInputBoundary interactor = new FcRInteractor(gateway, presenter);
        interactor.delete(new FcRRequestModel(0,0));
    }

    @Test
    public void delete_failure_flashcard_not_in_flashcard_set(){
        IFlashcardDataAccess flashcardDataAccess = new InMemoryFlashcard();
        IFlashcardSetDataAccess flashcardSetDataAccess = new InMemoryFlashcardSet();
        DBGateway gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("title",
                "description", true, 0,"User", new ArrayList<>()));
        flashcardSetDataAccess.saveFlashcardSet(new FlashcardSetDsRequestModel("title",
                "description", true, 1,"User", new ArrayList<>()));
        gateway.saveFlashcard(new FlashcardDsRequestModel("term", "definition",
                LocalDateTime.now(), 0,1));
        FcROutputBoundary presenter = new FcRResponsePresenter(){
            @Override
            public FcRResponseModel prepareSuccessView(FcRResponseModel responseModel){
                Assertions.fail("Unexpected success.");
                return null;
            }

            @Override
            public FcRResponseModel prepareFailView(String error){
                Assertions.assertEquals("Flashcard not in this flashcard set.", error);
                return null;
            }
        };
        FcRInputBoundary interactor = new FcRInteractor(gateway, presenter);
        interactor.delete(new FcRRequestModel(0,0));
    }
}
