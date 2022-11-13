package studyMode;
// use case layer

import dataAccess.DBGateway;
import entities.*;
import entityRequestModels.*;

import java.util.List;

public class FlashcardStudierBuilder {

    FlashcardFactory cardFactory = new FlashcardFactory();
    FlashcardStudierFactory studierFactory = new FlashcardStudierFactory();
    DBGateway gateway;
    FlashcardStudierBuilder(DBGateway gateway){
        this.gateway = gateway;
    }

    public Flashcard buildFlashcard(int flashcardID) {

       FlashcardDsRequestModel request = gateway.getFlashcard(flashcardID);

       return cardFactory.create(request.getTerm(), request.getDefinition(), request.getCreationDate(),
               request.getFlashcardId(), request.getBelongsToId());
    }

    public FlashcardStudier buildStudier(int flashcardSetId, boolean defaultDisplay){
        FlashcardSetDsRequestModel request = gateway.getFlashcardSet(flashcardSetId);

        FlashcardStudier studier = studierFactory.create(request.getTitle(), request.getDescription(),
                request.getIsPrivate(),flashcardSetId, request.getOwnerUsername(), defaultDisplay);

        List<Integer> flashcardIds = request.getFlashcardIds();
        for (int flashcardId : flashcardIds){
            Flashcard f = this.buildFlashcard(flashcardId);
            studier.addFlashcard(f);
        }
        studier.prepareForStudying();
        return studier;
    }

}
