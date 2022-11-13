package entities.flashcard;

import java.time.LocalDateTime;

public class FlashcardFactory {

    public Flashcard create(String term, String definition, LocalDateTime creationDate,
                     int flashcardId, int belongsToId){
        return new Flashcard(term, definition, creationDate, flashcardId, belongsToId);
    }
}
