package structure.users;

import structure.classes.Timetable;

public class Professor extends EducationalUser {
    private TeachingDegree degree;

    public Professor(String email, String lastName, String firstName, TeachingDegree degree) {
        super(email, lastName, firstName);
        this.degree = degree;
    }

    public Timetable getTimetable() {
        return new Timetable();
    }
}
