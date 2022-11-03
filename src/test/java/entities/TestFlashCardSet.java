package entities;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestFlashCardSet {
    @Test
    public void TestFlashCardSetEdit() {
        FlashcardSet fs = new FlashcardSet("Title", "Description",true, 1);
        fs.setTitle("Title2");
        Assertions.assertEquals("Title2", fs.getTitle());
    }
}
