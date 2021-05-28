package structure.classes;

import services.AppService;
import structure.users.Professor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Laboratory extends Class {
    private int group;
    public Laboratory(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime finishTime, Professor teachingProfessor, int group) {
        super(dayOfWeek, startTime, finishTime, teachingProfessor);
        this.group = group;
    }

    public Laboratory(ResultSet dbResult, AppService service) throws SQLException {
        super(
                DayOfWeek.valueOf(dbResult.getString("day_of_week").toUpperCase()),
                LocalTime.of(dbResult.getInt("start_hour"), dbResult.getInt("start_minute")),
                LocalTime.of(dbResult.getInt("finish_hour"), dbResult.getInt("finish_minute")),
                (Professor)service.getUserById(dbResult.getInt("professor_id"))
        );
        this.group = dbResult.getInt("group_number");
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Laboratory: ")
                .append(this.dayOfWeek.toString().substring(0, 1).toUpperCase())
                .append(this.dayOfWeek.toString().substring(1).toLowerCase())
                .append(", ")
                .append(this.startTime.toString())
                .append(" - ")
                .append(this.finishTime.toString())
                .toString();
    }
}
