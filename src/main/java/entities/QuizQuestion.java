package entities;

import entities.Flashcard;

import java.util.List;

/**
 * Abstract class representing a quiz question.
 * @author Anthony
 */
abstract public class QuizQuestion {
    private String actualAnswer;
    private String userAnswer;

    /**
     * Given a set of flashcards and an index for a specific flashcard, this method will generate a corresponding
     * question. The implementation is specified by the question type.
     * @param flashcards a list of flashcards
     * @param index the index of a specific flashcard in which the question will be based on
     */
    abstract public void generateQuestion(List<Flashcard> flashcards, int index);

    /**
     * @return true if the user answer is equal to the actual answer.
     */
    public boolean isCorrect(){
        return this.actualAnswer.equalsIgnoreCase(this.userAnswer);
    }

    /**
     * Gets the actual answer.
     * @return the actual answer
     */
    public String getActualAnswer() {
        return actualAnswer;
    }

    /**
     * Sets the actual answer.
     * @param actualAnswer the actual answer
     */
    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = actualAnswer;
    }

    /**
     * Sets the user answer. If the user answer is null or empty, the user answer should be set to null.
     * @param userAnswer the user answer
     */
    public void setUserAnswer(String userAnswer) {
        if (userAnswer == null || userAnswer.equals("")) {
            this.userAnswer = null;
        } else {
            this.userAnswer = userAnswer;
        }
    }
}
