package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * MultipleChoiceQuestion
 * @author Anthony
 */
public class MultipleChoiceQuestion extends QuizQuestion {
    /* If given term is true, then the term is the initial prompt.
    If given term is false, then the definition is the initial prompt.
     */
    private final boolean givenTerm;

    private String question;
    private final String[] choices;
    private final int numChoices = 4;

    private static final Random rand = new Random();

    /**
     * Constructs a multiple choice question.
     * @param flashcards the list of flashcards
     * @param index the index of a specific flashcard
     */
    public MultipleChoiceQuestion(List<Flashcard> flashcards, int index) {
        this.givenTerm = rand.nextBoolean();
        this.choices = new String[numChoices];
        generateQuestion(flashcards, index);
    }

    /**
     * Generates a multiple choice question.
     * @param flashcards a list of flashcards
     * @param index      the index of a specific flashcard in which the question will be based on
     */
    @Override
    public void generateQuestion(List<Flashcard> flashcards, int index) {
        Flashcard flashcard = flashcards.get(index);
        List<Flashcard> otherOptions = new ArrayList<>(flashcards); // shallow copy
        otherOptions.remove(flashcard); // list of all other options
        Collections.shuffle(otherOptions);
        List<Flashcard> preparedList = new ArrayList<>(otherOptions.subList(0, this.numChoices - 1));
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

    /**
     * Returns the string representation of the multiple choice question.
     * @return string representation
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Prompt: " + this.question + "\n");
        for (int i = 0; i < this.numChoices; i++) {
            s.append("Option ").append(i + 1).append(": ").append(this.choices[i]).append("\n");
        }
        return s.toString();
    }

    /**
     * Gets the question.
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the choices.
     * @return the choices
     */
    public String[] getChoices() {
        return choices;
    }
}
