package quiz_use_case;

import frameworks_and_drivers.database.DBGateway;
import entities.Flashcard;
import entities.FlashcardFactory;
import entities.Quiz;
import entities.QuizSettings;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Quiz Builder.
 * @author Anthony
 */
public class   QuizBuilder {
    private final FlashcardFactory flashcardFactory;
    private final DBGateway gateway;

    /**
     * Constructs a quiz builder.
     * @param gateway the gateway
     */
    public QuizBuilder(DBGateway gateway) {
        this.flashcardFactory = new FlashcardFactory();
        this.gateway = gateway;
    }

    /**
     * Builds a flashcard object given a flashcard ID.
     * @param flashcardID the flashcard ID
     * @return a flashcard object
     */
    public Flashcard buildFlashcard(int flashcardID) {
        FlashcardDsRequestModel request = gateway.getFlashcard(flashcardID);

        return flashcardFactory.create(request.getTerm(), request.getDefinition(), request.getCreationDate(),
                request.getFlashcardId(), request.getBelongsToId());
    }

    /**
     * Builds a quiz given a flashcard set ID and quiz settings.
     * @param flashcardSetID the flashcard set ID
     * @param quizSettings the quiz settings
     * @return a quiz object
     */
    public Quiz buildQuiz(int flashcardSetID, QuizSettings quizSettings) {
        FlashcardSetDsRequestModel request = gateway.getFlashcardSet(flashcardSetID);

        // create list of flashcards
        List<Flashcard> flashcards = new ArrayList<>();
        List<Integer> flashcardIDs = request.getFlashcardIds();
        for (int flashcardID : flashcardIDs) {
            Flashcard f = this.buildFlashcard(flashcardID);
            flashcards.add(f);
        }

        return new Quiz(quizSettings, flashcards);
    }
}
