package Editor.Test;

import dataAccess.FlashcardDataAccess;
import entityRequestModels.FlashcardDsRequestModel;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class FlashcardDataAcessTest {
    private final String path = "src/data/Flashcards.csv";
    private FlashcardDataAccess ffc;

    @Test
    public void testReadCSV() throws IOException {
        ffc = new FlashcardDataAccess(path);
        FlashcardDsRequestModel home = new FlashcardDsRequestModel("home", "place/person that feels safe", LocalDateTime.now(), 1, 0);
        FlashcardDsRequestModel bubly = new FlashcardDsRequestModel( "bubly", "sparkling water brand", LocalDateTime.now(), 2, 0);
        ffc.saveFlashcard(home);
        ffc.saveFlashcard(bubly);
        FlashcardDsRequestModel actual = ffc.getFlashcard(2);
        assertEquals("incorrect", "bubly", actual.getTerm());
        assertEquals("incorrect", "sparkling water brand", bubly.getDefinition());
    }

    @Test
    public void testEditCSV() throws IOException {
        ffc = new FlashcardDataAccess(path);
        FlashcardDsRequestModel home = new FlashcardDsRequestModel("home", "place/person that feels safe", LocalDateTime.now(), 1, 0);
        FlashcardDsRequestModel bubly = new FlashcardDsRequestModel( "bubly", "sparkling water brand", LocalDateTime.now(), 2, 0);
        ffc.saveFlashcard(home);
        ffc.saveFlashcard(bubly);

        FlashcardDsRequestModel changeDescription = new FlashcardDsRequestModel( "bubly", "refreshing", bubly.getCreationDate(), bubly.getFlashcardId(), bubly.getBelongsToId());
        ffc.editFlashcard(changeDescription);
        FlashcardDsRequestModel actual = ffc.getFlashcard(2);
        assertEquals("incorrect", "bubly", actual.getTerm());
        assertEquals("incorrect", "refreshing", actual.getDefinition());
    }
}
