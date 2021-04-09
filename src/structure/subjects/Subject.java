package structure.subjects;

import structure.classes.Class;
import structure.classes.Timetable;
import structure.quizzes.Quiz;

import java.util.TreeSet;

public class Subject {
    protected String name;
    protected Timetable timetable;
    protected TreeSet<Quiz> quizzes;

    public Subject(String name) {
        this.name = name;
        this.timetable = new Timetable();
    }

    public Subject addClass(Class newClass) {
        this.timetable.addClass(newClass);
        return this;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public String getName() { return this.name; }

    public Subject addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
        return this;
    }
}
