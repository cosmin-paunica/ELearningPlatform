package structure.classes;

import structure.users.Professor;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lecture extends Class {
    public Lecture(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime finishTime, Professor teachingProfessor) {
        super(dayOfWeek, startTime, finishTime, teachingProfessor);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Lecture: ")
                .append(this.dayOfWeek.toString().substring(0, 1).toUpperCase())
                .append(this.dayOfWeek.toString().substring(1).toLowerCase())
                .append(", ")
                .append(this.startTime.toString())
                .append(" - ")
                .append(this.finishTime.toString())
                .toString();
    }
}
