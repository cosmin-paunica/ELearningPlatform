package structure.classes;

import structure.users.Professor;

import java.time.DayOfWeek;
import java.time.LocalTime;

public abstract class Class implements Comparable<Class> {
    protected DayOfWeek dayOfWeek;
    protected LocalTime startTime;
    protected LocalTime finishTime;
    private Professor teachingProfessor;

    public Class(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime finishTime, Professor teachingProfessor) {
        this.setDayOfWeek(dayOfWeek);
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
        this.teachingProfessor = teachingProfessor;
    }

    protected void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    protected void setStartTime(LocalTime startTime) {
        this.startTime = LocalTime.of(startTime.getHour(), startTime.getMinute());
    }

    public LocalTime getStartTime() {
        return LocalTime.of(this.startTime.getHour(), this.startTime.getMinute());
    }

    protected void setFinishTime(LocalTime finishTime) {
        this.finishTime = LocalTime.of(finishTime.getHour(), finishTime.getMinute());
    }

    public LocalTime getFinishTime() {
        return LocalTime.of(this.finishTime.getHour(), this.finishTime.getMinute());
    }

    public Professor getTeachingProfessor() {
        return teachingProfessor;
    }

    public void changeTime(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime finishTime) {
        this.setDayOfWeek(dayOfWeek);
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
    }

    @Override
    public int compareTo(Class otherClass) {
        int comparisonByDayOfWeek = this.dayOfWeek.compareTo(otherClass.dayOfWeek);
        if (comparisonByDayOfWeek != 0)
            return comparisonByDayOfWeek;

        return this.startTime.compareTo(otherClass.startTime);
    }

    @Override
    public abstract String toString();
}
