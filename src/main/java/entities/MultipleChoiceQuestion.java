package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MultipleChoiceQuestion extends QuizQuestion {
    private final boolean givenTerm;

    private String question;
    private final String[] choices;
    private final int numChoices = 4;

    private static final Random rand = new Random();

    public MultipleChoiceQuestion(List<Flashcard> flashcards, int index) {
        this.givenTerm = rand.nextBoolean();
        this.choices = new String[numChoices];
        generateQuestion(flashcards, index);
    }

    @Override
    public void generateQuestion(List<Flashcard> flashcards, int index) {
        Flashcard flashcard = flashcards.get(index);
        List<Flashcard> otherOptions = new ArrayList<>(flashcards); // shallow copy
        otherOptions.remove(flashcard); // list of all other options
        Collections.shuffle(otherOptions);
        List<Flashcard> preparedList = new ArrayList<Flashcard>(otherOptions.subList(0, this.numChoices - 1));
        preparedList.add(flashcard);
        Collections.shuffle(preparedList); // shuffle the prepared list

        if (this.givenTerm) {
            this.question = flashcard.getTerm();
            this.setActualAnswer(flashcard.getDefinition());
            for (int i = 0; i < this.numChoices; i++) {
                this.choices[i] = preparedList.get(i).getDefinition();
            }
        } else {
            this.question = flashcard.getDefinition();
            this.setActualAnswer(flashcard.getTerm());
            for (int i = 0; i < this.numChoices; i++) {
                this.choices[i] = preparedList.get(i).getTerm();
            }
        }
    }

    @Override
    public String toString() {
        String s = "Prompt: " + this.question + "\n";
        for (int i = 0; i < this.numChoices; i++) {
            s += "Option " + (i+1) + ": " + this.choices[i] + "\n";
        }
        return s;
    }
}
