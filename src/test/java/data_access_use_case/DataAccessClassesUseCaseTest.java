package data_access_use_case;

import frameworks_and_drivers.database.CommonUserDataAccess;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import java.util.List;


public class DataAccessClassesUseCaseTest {

    private static final String flashcardPath = "src/test/java/data_access_use_case/test_data/Flashcards.csv";

    private static final String flashcardSetPath = "src/test/java/data_access_use_case/test_data/FlashcardSets.csv";

    private static final String userPath = "src/test/java/data_access_use_case/test_data/Users.csv";


    DBGateway setup() throws IOException {
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(flashcardPath);
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(flashcardSetPath);
        IUserDataAccess userGateway = new CommonUserDataAccess(userPath);

        return new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);
    }

    // Test for user data access
    @Test
    void testUserData() throws IOException {
        DBGateway gateway = this.setup();

        List<Integer> testIdList = List.of(0);
        Assertions.assertTrue(gateway.existsByName("testUser1"));
        Assertions.assertEquals(testIdList, gateway.getUser("testUser1").getFlashcardSetIds());
        Assertions.assertFalse(gateway.getUser("testUser1").getIsAdmin());
        Assertions.assertEquals("testUser1", gateway.getUser("testUser1").getUsername());
        Assertions.assertEquals("search", gateway.getUser("testUser1").getPassword());

        Assertions.assertEquals("src/data/Users.csv", DBGateway.getUserPath());
    }

    // test for flashcard access data
    @Test
    void testFlashcardData() throws IOException {
        DBGateway gateway = this.setup();

        Assertions.assertEquals(0, gateway.getFlashcard(0).getFlashcardId());
        Assertions.assertEquals("test card 1", gateway.getFlashcard(0).getTerm());
        Assertions.assertEquals("the first test card", gateway.getFlashcard(0).getDefinition());
        Assertions.assertEquals(0, gateway.getFlashcard(0).getBelongsToId());
        Assertions.assertEquals(LocalDateTime.parse("2022-11-13T15:32:26.666982800"), gateway.getFlashcard(0).getCreationDate());

        Assertions.assertEquals("src/data/Flashcards.csv", DBGateway.getFlashcardPath());
    }

    // test for flashcard set data access
    @Test
    void testFlashcardSetData() throws IOException {
        DBGateway gateway = this.setup();

        Assertions.assertEquals(0, gateway.getFlashcardSet(0).getFlashcardSetId());
        Assertions.assertEquals("test1", gateway.getFlashcardSet(0).getTitle());
        Assertions.assertEquals("d1", gateway.getFlashcardSet(0).getDescription());
        Assertions.assertEquals("testUser1", gateway.getFlashcardSet(0).getOwnerUsername());
        List <Integer> testFlashcardIdList = List.of(0);
        Assertions.assertEquals(testFlashcardIdList, gateway.getFlashcardSet(0).getFlashcardIds());
        Assertions.assertEquals(false, gateway.getFlashcardSet(0).getIsPrivate());

        Assertions.assertEquals("src/data/FlashcardSets.csv", DBGateway.getFlashcardSetPath());
    }
}
