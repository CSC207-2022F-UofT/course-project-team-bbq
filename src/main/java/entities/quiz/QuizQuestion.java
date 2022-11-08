package entities.quiz;

import entities.flashcard.Flashcard;
import entities.flashcard.FlashcardSet;

abstract public class QuizQuestion {
    private String question;
    private String actualAnswer;
    private String userAnswer;

    public QuizQuestion(FlashcardSet flashcardSet, int index) {
        generateQuestion(flashcardSet, index);
    }

    abstract public void generateQuestion(FlashcardSet flashcardSet, int index);

    public boolean isCorrect(){
        return this.actualAnswer.equals(this.userAnswer);
    }

    /* GETTERS AND SETTERS */
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getActualAnswer() {
        return actualAnswer;
    }

    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = actualAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
