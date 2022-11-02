package dataAccess;

import entities.FlashcardSet;
import entities.User;

public interface IUserDataAccess {
    static String database = "src/data/Users.csv";

    /**
     * @param username the user's username
     * @return the User object who has username
     */
    User getUser(String username);

    /**
     * @param flashcardSet
     *
     * Add the data from flashcardSet to the FlashcardSet database and
     * add the FlashcardSet ID to the user database
     */
    void addFlashcardSet(FlashcardSet flashcardSet);

    /**
     * @param flashcardSetId
     * Delete the flashcardSet ID from the User database and delete the
     * FlashcardSet from the flashcard set database
     */
    void deleteFlashcardSet(String flashcardSetId);
}
