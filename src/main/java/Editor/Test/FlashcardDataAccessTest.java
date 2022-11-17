package Editor.Test;

import dataAccess.FlashcardDataAccess;
import entityRequestModels.FlashcardDsRequestModel;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class FlashcardDataAccessTest{
    private final String path = "src/data/Flashcards.csv";


    @Test
    public void testReadCSV() throws IOException {
        FlashcardDataAccess ffc = new FlashcardDataAccess(path);
        FlashcardDsRequestModel home = new FlashcardDsRequestModel("home", "place/person that feels safe", LocalDateTime.now(), 1, 0);
        FlashcardDsRequestModel bubbly = new FlashcardDsRequestModel( "bubbly", "sparkling water brand", LocalDateTime.now(), 2, 0);
        ffc.saveFlashcard(home);
        ffc.saveFlashcard(bubbly);

        FlashcardDsRequestModel actual = ffc.getFlashcard(2);
        assertEquals("incorrect", "bubbly", actual.getTerm());
        assertEquals("incorrect", "sparkling water brand", actual.getDefinition());
    }

    @Test
    public void testEditCSV() throws IOException {
        FlashcardDataAccess ffc = new FlashcardDataAccess(path);
        FlashcardDsRequestModel home = new FlashcardDsRequestModel("hello", "greeting", LocalDateTime.now(), 1, 0);
        FlashcardDsRequestModel bubbly = new FlashcardDsRequestModel( "bubbly", "sparkling water brand", LocalDateTime.now(), 2, 0);
        ffc.saveFlashcard(home);
        ffc.saveFlashcard(bubbly);

        FlashcardDsRequestModel changeDescription = new FlashcardDsRequestModel( "bubbly", "refreshing", bubbly.getCreationDate(), bubbly.getFlashcardId(), bubbly.getBelongsToId());
        ffc.editFlashcard(changeDescription);
        FlashcardDsRequestModel actual = ffc.getFlashcard(2);
        assertEquals("incorrect", "bubbly", actual.getTerm());
        assertEquals("incorrect", "refreshing", actual.getDefinition());
    }
}