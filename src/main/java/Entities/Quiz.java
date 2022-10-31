package Entities;

import java.util.ArrayList;
import java.util.HashMap;

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
         * for each section, generate the associated question
         * append to quizQuestions
         */


    }

    public static void main(String[] args) {

    }
}
