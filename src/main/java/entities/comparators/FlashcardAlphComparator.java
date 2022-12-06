package entities.comparators;

import entities.Flashcard;

import java.util.Comparator;

/**
 * A class which knows how to compare flashcard's by term
 */
public class FlashcardAlphComparator implements Comparator<Flashcard> {

    /**
     * @param f1 the first flashcard to be compared.
     * @param f2 the second flashcard to be compared.
     * @return an integer which indicates which string comes first alphabetically
     */
    @Override
    public int compare(Flashcard f1, Flashcard f2) {
       String term1 = f1.getTerm();
       String term2 = f2.getTerm();

       return term1.compareTo(term2);
    }
}
