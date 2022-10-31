package Entities;

abstract public class QuizQuestion {
    String question;
    String actualAnswer;
    String userAnswer;

    abstract public boolean isCorrect();
}
