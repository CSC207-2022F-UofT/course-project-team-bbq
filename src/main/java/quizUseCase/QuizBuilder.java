package quizUseCase;

import dataAccess.DBGateway;
import entities.Flashcard;
import entities.FlashcardFactory;
import entities.Quiz;
import entities.QuizSettings;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

import java.util.ArrayList;
import java.util.List;

public class QuizBuilder {
    private final FlashcardFactory flashcardFactory;
    private final DBGateway gateway;

    public QuizBuilder(DBGateway gateway) {
        this.flashcardFactory = new FlashcardFactory();
        this.gateway = gateway;
    }

    public Flashcard buildFlashcard(int flashcardID) {
        FlashcardDsRequestModel request = gateway.getFlashcard(flashcardID);

        return flashcardFactory.create(request.getTerm(), request.getDefinition(), request.getCreationDate(),
                request.getFlashcardId(), request.getBelongsToId());
    }

    public Quiz buildQuiz(int flashcardSetId, QuizSettings quizSettings) {
        FlashcardSetDsRequestModel request = gateway.getFlashcardSet(flashcardSetId);

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
