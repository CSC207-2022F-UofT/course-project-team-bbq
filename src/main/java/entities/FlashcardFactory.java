package entities;

import java.time.LocalDateTime;

public class FlashcardFactory {

    /**
     * @param term the flashcard's term
     * @param definition the definition of the flashcard's term
     * @param creationDate the time the flashcard was initially created at
     * @param flashcardId the flashcard's unique id
     * @param belongsToId the unique id of the flashcard set this flashcard belongs to
     * @return a new flashcard with the given fields
     */
    public Flashcard create(String term, String definition, LocalDateTime creationDate,
                     int flashcardId, int belongsToId){
        return new Flashcard(term, definition, creationDate, flashcardId, belongsToId);
    }
}
