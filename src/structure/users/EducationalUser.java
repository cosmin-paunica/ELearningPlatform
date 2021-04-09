package structure.users;

import structure.classes.Timetable;

public abstract class EducationalUser extends User {
    protected Timetable timetable;

    public EducationalUser(String email, String lastName, String firstName) {
        super(email, lastName, firstName);
        this.timetable = null;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = new Timetable(timetable);
    }

    public Timetable getTimetable() {
        return this.timetable;
    }
}
