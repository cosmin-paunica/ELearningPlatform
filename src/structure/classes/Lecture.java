package structure.classes;

import structure.subjects.Subject;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lecture extends Class {
    public Lecture(Subject subject, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime finishTime) {
        super(subject, dayOfWeek, startTime, finishTime);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.subject.getName())
                .append(" lecture: ")
                .append(this.dayOfWeek.toString().substring(0, 1).toUpperCase())
                .append(this.dayOfWeek.toString().substring(1).toLowerCase())
                .append(", ")
                .append(this.startTime.toString())
                .append(" - ")
                .append(this.finishTime.toString())
                .toString();
    }
}
