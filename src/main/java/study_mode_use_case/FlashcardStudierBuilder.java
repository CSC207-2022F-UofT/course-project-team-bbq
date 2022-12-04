package study_mode_use_case;
// use case layer

import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import entities.*;

import java.util.List;

/**
 * A class that knows how to build a FlashcardStudier from the database.
 * <p>
 * Application Business Rules.
 * @author Lucas Prates
 */
public class FlashcardStudierBuilder {

    /**
     * a class that knows how to create a flashcard
     */
    final FlashcardFactory cardFactory = new FlashcardFactory();

    /**
     * a class that knows how to create a flashcard studier
     */
    final FlashcardStudierFactory studierFactory = new FlashcardStudierFactory();

    /**
     * an object which accesses the database
     */
    final DBGateway gateway;

    /**
     * @param gateway an object which gives this class database access
     */
    FlashcardStudierBuilder(DBGateway gateway){
        this.gateway = gateway;
    }

    /**
     * @param flashcardID the ID of a flashcard in the desired flashcard set
     * @return an in memory Flashcard constructed from the database entry
     */
    public Flashcard buildFlashcard(int flashcardID) {

       FlashcardDsRequestModel request = gateway.getFlashcard(flashcardID);

       return cardFactory.create(request.getTerm(), request.getDefinition(), request.getCreationDate(),
               request.getFlashcardId(), request.getBelongsToId());
    }

    /**
     * @param flashcardSetId the ID of the flashcard set we want to build a studier for
     * @param defaultDisplay true if you want the flashcard to display 'term' by default,
     *                       false if you want the flashcard to display 'definition' by default
     * @return an in memory FlashcardStudier constructed from the database entry
     */
    public FlashcardStudier buildStudier(int flashcardSetId, boolean defaultDisplay){
        FlashcardSetDsRequestModel request = gateway.getFlashcardSet(flashcardSetId);

        FlashcardStudier studier = studierFactory.create(request.getTitle(), request.getDescription(),
                request.getIsPrivate(),flashcardSetId, request.getOwnerUsername(), defaultDisplay);

        List<Integer> flashcardIds = request.getFlashcardIds();
        for (int flashcardId : flashcardIds){
            Flashcard f = this.buildFlashcard(flashcardId);
            studier.addFlashcard(f);
        }

        studier.setNumFlashcards();
        return studier;
    }

}
