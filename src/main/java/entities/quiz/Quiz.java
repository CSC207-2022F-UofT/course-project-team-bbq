package entities.quiz;

import java.util.ArrayList;

public class Quiz {
    ArrayList<QuizQuestion> quizQuestions = new ArrayList<QuizQuestion>();
    int num;
    QuizSettings quizSettings = new QuizSettings();

    public Quiz(int num) {
        this.num = num;
    }

    public void generateQuestions() {
        /*
         * Use number of questions
         * divide the questions among multiple choice, true false, and text entry
         * copy the flashcard set array, shuffle it, take the first num and truncate the array
         * for each section, take a flashcard and generate the associated question
         *
         * append to quizQuestions
         */


    }

    public static void main(String[] args) {

    }
}
