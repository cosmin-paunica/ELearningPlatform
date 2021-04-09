package structure.classes;

import structure.users.Professor;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Seminar extends Class {
    private int group;

    public Seminar(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime finishTime, Professor teachingProfessor, int group) {
        super(dayOfWeek, startTime, finishTime, teachingProfessor);
        this.group = group;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Seminar: ")
                .append(this.dayOfWeek.toString().substring(0, 1).toUpperCase())
                .append(this.dayOfWeek.toString().substring(1).toLowerCase())
                .append(", ")
                .append(this.startTime.toString())
                .append(" - ")
                .append(this.finishTime.toString())
                .toString();
    }
}
