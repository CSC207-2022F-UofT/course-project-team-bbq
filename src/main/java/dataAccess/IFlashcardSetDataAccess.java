package dataAccess;

import entities.Flashcard;
import entities.FlashcardSet;

import java.util.HashMap;

public interface IFlashcardSetDataAccess {

    static String database = "src/data/FlashCardSets.csv";


    FlashcardSet getFlashcardSetByID(int flashcardSetID);

    String[] getTitleAndDescription(int flashcardSetID);

    void deleteFlashcardSet(int flashcardSetID);

    void saveFlashcardSet(FlashcardSet flashcardSet);

    void deleteFlashcard(int flashcardID);

    void editFlashcard(int flashcardID);

    void addFlashcard(Flashcard flashcard);



}
