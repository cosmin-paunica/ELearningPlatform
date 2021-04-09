package structure.subjects;

import structure.classes.Class;
import structure.classes.Timetable;
import structure.quizzes.Quiz;

import java.util.ArrayList;

public class Subject {
    protected String name;
    protected Timetable timetable;
    protected ArrayList<Quiz> quizzes;

    public Subject(String name) {
        this.name = name;
        this.timetable = new Timetable();
        this.quizzes = new ArrayList<>();
    }

    public Subject addClass(Class newClass) {
        this.timetable.addClass(newClass);
        return this;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public String getName() { return this.name; }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public Subject addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
        return this;
    }
}
