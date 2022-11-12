package studyMode;
// use case layer

import dataAccess.DBGateway;
import entities.*;
import entityRequestModels.*;

public class FlashcardStudierBuilder {

    FlashcardFactory cardFactory = new FlashcardFactory();
    FlashcardSetFactory setFactory = new FlashcardSetFactory();
    DBGateway gateway;
    FlashcardStudierBuilder(DBGateway gateway){
        this.gateway = gateway;
    }

    public Flashcard buildFlashcard(int flashcardID) {
        return null;
    }

    public FlashcardSet buildFlashcardSet(int flashcardSetId){
        return null;
    }

    public FlashcardStudier buildStudier(int flashcardSetId){
        return null;
    }

}
