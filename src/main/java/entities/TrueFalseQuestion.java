package entities;

public class TrueFalseQuestion extends QuizQuestion {
    private String definition;
    private boolean pranked = false;

    public TrueFalseQuestion(Flashcard flashcard, FlashcardSet flashcardSet) {
        // pick a random value for pranked
        generateQuestion(flashcard, flashcardSet);
    }

    public void generateQuestion(Flashcard flashcard, FlashcardSet flashcardSet){
        String term = flashcard.getTerm();
        String definition = flashcard.getDefinition();
        this.setQuestion(term);
        this.setActualAnswer(definition);
        if (pranked) {
            // take a random flashcard definition from flashcard set.
        } else {
            this.definition = definition;
        }
    }
}
