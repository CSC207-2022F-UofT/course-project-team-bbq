package entities.comparators;

import entities.Flashcard;

import java.util.Comparator;

public class FlashcardByDateComparator implements Comparator<Flashcard> {
    @Override
    public int compare(Flashcard f1, Flashcard f2) {
        return f1.getCreationDate().compareTo(f2.getCreationDate());
    }
}
