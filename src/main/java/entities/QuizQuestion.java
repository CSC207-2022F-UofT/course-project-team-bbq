package entities;

abstract public class QuizQuestion {
    private String question;
    private String actualAnswer;
    private String userAnswer;

    abstract public void generateQuestion(Flashcard flashcard, FlashcardSet flashcardSet);

    public boolean isCorrect(){
        return this.actualAnswer.equals(this.userAnswer);
    }

    // getters and setters
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
