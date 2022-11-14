package Editor.Test;
import Editor.Flashcard.FlashcardEditorInputBoundary;
import Editor.Flashcard.FlashcardEditorInteractor;
import Editor.Flashcard.FlashcardEditorOutputBoundary;
import Editor.Flashcard.FlashcardEditorRequestModel;
import Editor.Flashcard.screens.FlashcardEditorPresenter;
import dataAccess.DBGateway;
import dataAccess.FlashcardDataAccess;
import entityRequestModels.FlashcardDsRequestModel;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FlashcardInteractorTest {

    @Test
    public void editInInteractor() throws IOException {
        DBGateway dbGateway;

        FlashcardDataAccess fcDataAccess = new FlashcardDataAccess("src/data/Flashcards.csv");
        dbGateway = new DBGateway(fcDataAccess, null, null);
        FlashcardEditorOutputBoundary presenter = new FlashcardEditorPresenter();

        FlashcardEditorInputBoundary interactor = new FlashcardEditorInteractor(dbGateway, presenter);

        FlashcardEditorRequestModel newFlashcard = new FlashcardEditorRequestModel(1, "b", "a");
        interactor.edit(newFlashcard);

        FlashcardDsRequestModel actual = fcDataAccess.getFlashcard(1);
        assertEquals("b", actual.getTerm());
        assertEquals("a", actual.getDefinition());

    }

}
