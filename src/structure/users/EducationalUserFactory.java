package structure.users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationalUserFactory {
    public EducationalUser getUser(ResultSet dbResult) {
        EducationalUser newUser = null;
        try {
            String userType = dbResult.getString("user_type").toLowerCase();
            if (userType.equals("student")) {
                newUser = new Student(dbResult);
            } else if (userType.equals("professor")) {
                newUser = new Professor(dbResult);
            } else {
                throw new UnsupportedOperationException("Unsupported user type: " + userType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUser;
    }
}
