package structure.subjects;

import structure.quizzes.Quiz;
import structure.users.Professor;
import structure.users.Student;

import java.util.ArrayList;
import java.util.TreeSet;

public class Subject {
    protected String name;
    protected ArrayList<Professor> teachingProfessors;
    protected ArrayList<Student> enrolledStudents;
    protected TreeSet<Quiz> quizzes;

    public Subject(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
