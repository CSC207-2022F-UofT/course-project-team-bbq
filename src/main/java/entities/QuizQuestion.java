package entities;

import entities.Flashcard;

import java.util.List;

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
        return this.actualAnswer.equals(this.userAnswer);
    }

    /** GETTERS AND SETTERS **/
    public String getActualAnswer() {
        return actualAnswer;
    }

    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = actualAnswer.toLowerCase();
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer.toLowerCase();
    }
}
