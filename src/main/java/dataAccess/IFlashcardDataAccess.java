package dataAccess;

import entities.Flashcard;

public interface IFlashcardDataAccess {

    static String database = "src/data/Flashcards.csv";

    Flashcard getFlashcardByID(String flashcardID);

    void addFlashcard(Flashcard flashcard);

    void editFlashcard(Flashcard flashcard);

    void deleteFlashcard(String flashcardID);
}
