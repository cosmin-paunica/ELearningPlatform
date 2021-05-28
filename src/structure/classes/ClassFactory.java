package structure.classes;

import services.AppService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassFactory {
    public Class getClass(ResultSet dbResult, AppService service) {
        Class newClass = null;
        try {
            String classType = dbResult.getString("class_type").toLowerCase();
            newClass = switch (classType) {
                case "lecture" -> new Lecture(dbResult, service);
                case "seminar" -> new Seminar(dbResult, service);
                case "laboratory" -> new Laboratory(dbResult, service);
                default -> throw new UnsupportedOperationException("Unsupported class type: " + classType);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newClass;
    }
}
