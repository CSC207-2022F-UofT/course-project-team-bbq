package entities;

import java.time.LocalDateTime;

public class FlashcardFactory {

    Flashcard create(String term, String definition, LocalDateTime creationDate,
                     int flashcardId, int belongsToId){
        return new Flashcard(term, definition, creationDate, flashcardId, belongsToId);
    }
}
