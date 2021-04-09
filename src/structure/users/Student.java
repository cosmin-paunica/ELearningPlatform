package structure.users;

import structure.subjects.Subject;

import java.util.ArrayList;

public class Student extends EducationalUser {
    protected int studyYear;
    protected ArrayList<Subject> enrolledSubjects;

    public Student(String email, String lastName, String firstName, int studyYear) {
        super(email, lastName, firstName);
        this.studyYear = studyYear;
        this.enrolledSubjects = new ArrayList<>();
    }
}
