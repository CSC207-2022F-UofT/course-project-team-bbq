package entities.comparators;

import entities.Flashcard;

import java.util.Comparator;

/**
 * A class which knows how to compare flashcards by date
 */
public class FlashcardByDateComparator implements Comparator<Flashcard> {
    /**
     * @param f1 the first flashcard to be compared.
     * @param f2 the second flashcard to be compared.
     * @return an integer which indicates which flashcard was created first
     */
    @Override
    public int compare(Flashcard f1, Flashcard f2) {
        return f1.getCreationDate().compareTo(f2.getCreationDate());
    }
}
