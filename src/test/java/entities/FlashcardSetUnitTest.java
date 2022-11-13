package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlashcardSetUnitTest {
    Flashcard f = new Flashcard("SOLID", "Single responsibility, Open/closed," +
            "Liskov substitution, " + "Interface segregation, Dependency inversion",
            LocalDateTime.now(), 1, 11);

    List<Flashcard> flashcardList = new ArrayList<>();

    FlashcardSet fs = new FlashcardSet("CSC207", "Final Exam Review", true, 11,
            "uncle_bob69", flashcardList);

    @Test
    public void testCreatedFlashcardSet() {
        Assertions.assertTrue(fs.getIsPrivate());
        Assertions.assertEquals("CSC207", fs.getTitle());
        Assertions.assertEquals(11, fs.getFlashcardSetId());
        Assertions.assertEquals("uncle_bob69", fs.getOwnerUsername());
        Assertions.assertEquals(0, flashcardList.size());
    }

    @Test
    public void testEditFlashcardSet() {
        fs.setIsPrivate(false);
        Assertions.assertFalse(fs.getIsPrivate());
    }

    @Test
    public void testAddFlashcardToFlashcardSet() {
        flashcardList.add(f);
        fs.setFlashcards(flashcardList);
        Assertions.assertEquals("SOLID", fs.getFlashcards().get(0).getTerm());
    }
}


