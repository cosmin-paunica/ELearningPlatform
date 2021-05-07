package structure.subjects;

import structure.classes.Class;
import structure.classes.Timetable;
import structure.quizzes.Quiz;

import java.util.ArrayList;

public class Subject {
    protected int id;
    protected String name;
    protected Timetable timetable;
    protected ArrayList<Quiz> quizzes;

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
        this.timetable = new Timetable();
        this.quizzes = new ArrayList<>();
    }

    public int getId() {
        return this.id;
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
