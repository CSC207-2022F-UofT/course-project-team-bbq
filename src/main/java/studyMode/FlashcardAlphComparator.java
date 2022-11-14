package studyMode;

import entities.Flashcard;

import java.util.Comparator;

public class FlashcardAlphComparator implements Comparator<Flashcard> {

    @Override
    public int compare(Flashcard f1, Flashcard f2) {
       String term1 = f1.getTerm();
       String term2 = f2.getTerm();

       return term1.compareTo(term2);
    }
}
