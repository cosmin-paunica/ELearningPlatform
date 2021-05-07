package structure.users;

import structure.classes.Class;
import structure.classes.Laboratory;
import structure.classes.Seminar;
import structure.classes.Timetable;
import structure.subjects.Subject;

import java.util.ArrayList;

public class Student extends EducationalUser {
    protected int group;
    protected ArrayList<Subject> enrolledSubjects;

    public Student(int id, String email, String lastName, String firstName, int group, String hashedPassword) {
        super(id, email, lastName, firstName, hashedPassword);
        this.group = group;
        this.enrolledSubjects = new ArrayList<>();
    }

    public int getGroup() {
        return group;
    }

    public Student enroll(Subject subject) {
        this.enrolledSubjects.add(subject);
        return this;
    }

    public Timetable getTimetable() {
        Timetable timetable = new Timetable();
        for (Subject subject : enrolledSubjects)
            for (Class newClass : subject.getTimetable().getClasses()) {
                if (newClass instanceof Laboratory) {
                    if (this.getGroup() == ((Laboratory) newClass).getGroup())
                        timetable.addClass(newClass);
                } else if (newClass instanceof Seminar) {
                    if (this.getGroup() == ((Seminar) newClass).getGroup())
                        timetable.addClass(newClass);
                } else {
                    timetable.addClass(newClass);
                }
            }
        return timetable;
    }

    public ArrayList<Subject> getEnrolledSubjects() {
        return enrolledSubjects;
    }
}
