package structure.classes;

import java.util.TreeSet;

public class Timetable {
    TreeSet<Class> classes;

    public Timetable() {
        classes = new TreeSet<>();
    }

    public Timetable(Timetable timetable) {
        this.classes = timetable.classes;
    }

    public Timetable addClass(Class newClass) {
        this.classes.add(newClass);
        return this;
    }

    public TreeSet<Class> getClasses() {
        return classes;
    }

    @Override
    public String toString() {
        StringBuilder strBldr = new StringBuilder();
        for (Class currentClass : this.classes) {
            strBldr.append(currentClass.toString());
            strBldr.append("\n");
        }
        return strBldr.substring(0, strBldr.length() - 1);
    }
}
