package structure.users;

import structure.classes.Timetable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor extends EducationalUser {
    private TeachingDegree degree;

    public Professor(int id, String email, String lastName, String firstName, TeachingDegree degree, String hashedPassword) {
        super(id, email, lastName, firstName, hashedPassword);
        this.degree = degree;
    }

    public Professor(ResultSet dbResult) throws SQLException {
        super(
                dbResult.getInt("user_id"),
                dbResult.getString("email"),
                dbResult.getString("last_name"),
                dbResult.getString("first_name"),
                dbResult.getString("password")
        );
        this.degree = TeachingDegree.fromString(dbResult.getString("degree"));
    }

    public Timetable getTimetable() {
        return new Timetable();
    }
}
