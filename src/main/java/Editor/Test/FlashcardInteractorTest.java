package Editor.Test;
import Editor.Flashcard.*;
import Editor.Flashcard.screens.InMemoryFlashcard;
import dataAccess.DBGateway;
import dataAccess.IFlashcardDataAccess;
import entityRequestModels.FlashcardDsRequestModel;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class FlashcardInteractorTest {

    @Test
    public void editSuccess(){
        IFlashcardDataAccess fcDataAccess = new InMemoryFlashcard();
        DBGateway dbGateway = new DBGateway(fcDataAccess, null, null);
        FlashcardEditorOutputBoundary presenter = new FlashcardEditorOutputBoundary(){

            @Override
            public FlashcardEditorResponseModel prepareSuccessView(FlashcardEditorResponseModel flashcard) {
                assertEquals("table", flashcard.getTermEdit());
                assertEquals("furniture to work on", flashcard.getDefinitionEdit());

                FlashcardDsRequestModel newFlashcard = fcDataAccess.getFlashcard(flashcard.getFlashcardId());
                assertEquals(newFlashcard.getTerm(), flashcard.getTermEdit());
                assertEquals(newFlashcard.getDefinition(), flashcard.getDefinitionEdit());
                return null;
            }

            @Override
            public FlashcardEditorResponseModel prepareFailView(String error) {
                fail("Failure is unexpected");
                return null;
            }
        };

        FlashcardEditorInputBoundary interactor = new FlashcardEditorInteractor(dbGateway, presenter);

        int flashcardId = 1;
        FlashcardDsRequestModel currentFlashcard = new FlashcardDsRequestModel("chair", "furniture to sit on", LocalDateTime.now(), flashcardId, 0);
        fcDataAccess.saveFlashcard(currentFlashcard);
        FlashcardEditorRequestModel inputData = new FlashcardEditorRequestModel(flashcardId, "table", "furniture to work on");

        interactor.edit(inputData);

    }

    @Test
    public void editFail(){
        IFlashcardDataAccess fcDataAccess = new InMemoryFlashcard();
        DBGateway dbGateway = new DBGateway(fcDataAccess, null, null);
        FlashcardEditorOutputBoundary presenter = new FlashcardEditorOutputBoundary(){

            @Override
            public FlashcardEditorResponseModel prepareSuccessView(FlashcardEditorResponseModel flashcard) {
                fail("Success is unexpected");
                return null;
            }

            @Override
            public FlashcardEditorResponseModel prepareFailView(String error) {
                assertEquals("Error: Term input cannot be empty.", error);
                return null;
            }
        };

        FlashcardEditorInputBoundary interactor = new FlashcardEditorInteractor(dbGateway, presenter);

        int flashcardId = 1;
        FlashcardDsRequestModel currentFlashcard = new FlashcardDsRequestModel("chair", "furniture to sit on", LocalDateTime.now(), flashcardId, 0);
        fcDataAccess.saveFlashcard(currentFlashcard);
        FlashcardEditorRequestModel inputData = new FlashcardEditorRequestModel(flashcardId, "", "furniture to work on");

        interactor.edit(inputData);

        //Check to see that nothing was changed when inputting an empty string as a Term.
        FlashcardDsRequestModel newFlashcard = fcDataAccess.getFlashcard(flashcardId);
        assertEquals("chair", newFlashcard.getTerm());
        assertEquals("furniture to sit on", newFlashcard.getDefinition());

    }

}