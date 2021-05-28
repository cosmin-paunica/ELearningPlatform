package services;

import structure.classes.ClassFactory;
import structure.subjects.Subject;
import structure.users.EducationalUserFactory;
import structure.users.Student;
import structure.users.User;

import java.sql.*;

public class DBService implements Loader, AutoCloseable {
    private static DBService instance;

    private Connection connection;

    private DBService() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:derby:elearningplatformDB;create=true");
    }

    public static DBService getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBService();
        }
        return instance;
    }

    private void extra() {
        insertInitialData();
    }

    public void loadUsers(AppService service) {
        try {
            EducationalUserFactory factory = new EducationalUserFactory();
            ResultSet results = connection.createStatement().executeQuery("SELECT * FROM users");
            while (results.next()) {
                service.addUser(factory.getUser(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadSubjects(AppService service) {
        try {
            ResultSet results = connection.createStatement().executeQuery("SELECT * FROM subjects");
            while (results.next()) {
                service.addSubject(new Subject(results.getInt(1), results.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadClasses(AppService service) {
        try {
            ClassFactory factory = new ClassFactory();
            ResultSet results = connection.createStatement().executeQuery("SELECT * FROM classes");
            while (results.next()) {
                Subject subject = service.getSubjectById(results.getInt("subject_id"));
                subject.addClass(factory.getClass(results, service));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadEnrollments(AppService service) {
        try {
            ResultSet results = connection.createStatement().executeQuery("SELECT * FROM enrollments");
            while (results.next()) {
                Student student = ((Student)service.getUserById(results.getInt(1)));
                Subject subject = service.getSubjectById(results.getInt(2));
                student.enroll(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean changePassword(User user, String newHashedPassword) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE customers SET password=? WHERE user_id=?"
            );
            statement.setString(1, newHashedPassword);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean enroll(User user, Subject subject) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO enrollments VALUES (?, ?)"
            );
            statement.setInt(1, user.getId());
            statement.setInt(2, subject.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() throws SQLException {
        connection.close();
    }

    private void createTables() throws SQLException {
        connection.createStatement().execute("" +
                "CREATE TABLE users (" +
                    "user_id INT PRIMARY KEY, " +
                    "user_type VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL, " +
                    "last_name VARCHAR(255) NOT NULL, " +
                    "first_name VARCHAR(255) NOT NULL, " +
                    "group_number INT DEFAULT NULL, " +
                    "degree VARCHAR(255) DEFAULT NULL, " +
                    "password VARCHAR(255)" +
                ")" +
        "");

        connection.createStatement().execute("" +
                "CREATE TABLE subjects (" +
                    "subject_id INT PRIMARY KEY, " +
                    "subject_name VARCHAR(255) NOT NULL" +
                ")" +
        "");

        connection.createStatement().execute("" +
                "CREATE TABLE classes (" +
                    "class_id INT PRIMARY KEY, " +
                    "subject_id INT NOT NULL, " +
                    "class_type VARCHAR(255) NOT NULL, " +
                    "day_of_week VARCHAR(255) NOT NULL, " +
                    "start_hour INT NOT NULL, " +
                    "start_minute INT NOT NULL, " +
                    "finish_hour INT NOT NULL, " +
                    "finish_minute INT NOT NULL, " +
                    "professor_id INT NOT NULL, " +
                    "group_number INT DEFAULT NULL" +
                ")" +
        "");

        connection.createStatement().execute("" +
                "ALTER TABLE classes " +
                "ADD CONSTRAINT fk_classes_1 FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) " +
        "");

        connection.createStatement().execute("" +
                "ALTER TABLE classes " +
                "ADD CONSTRAINT fk_classes_2 FOREIGN KEY (professor_id) REFERENCES users(user_id)" +
        "");

        connection.createStatement().execute("" +
                "CREATE TABLE enrollments (" +
                    "student_id INT NOT NULL, " +
                    "subject_id INT NOT NULL" +
                ")" +
        "");

        connection.createStatement().execute("" +
                "ALTER TABLE enrollments " +
                "ADD CONSTRAINT pk_enrollments PRIMARY KEY (student_id, subject_id)" +
        "");

        connection.createStatement().execute("" +
                "ALTER TABLE enrollments " +
                "ADD CONSTRAINT fk_enrollments_1 FOREIGN KEY (student_id) REFERENCES users(user_id) " +
        "");

        connection.createStatement().execute("" +
                "ALTER TABLE enrollments " +
                "ADD CONSTRAINT fk_enrollments_2 FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)" +
        "");
    }

    private void insertInitialData() {
        try {
            connection.createStatement().execute("" +
                    "INSERT INTO users " +
                    "VALUES (1, 'professor', 'bogdan.mihailescu@unibuc.ro', 'Mihailescu', 'Bogdan', NULL, 'lecturer', '45ab08cb9260d3f6fc09efa49fade3f3fcbb5c8d575e747295b2386d4b334169')" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO users " +
                    "VALUES (2, 'student', 'mihai.george@s.unibuc.ro', 'George', 'Mihai', 243, NULL, 'ebfea5e70682e16e5973d674121bb115c09fd166a9a5bf6df319734473e76f76')" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO subjects " +
                    "VALUES (1, 'Programare avansata pe obiecte')" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO subjects " +
                    "VALUES (2, 'Inteligenta artificiala')" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO subjects " +
                    "VALUES (3, 'Algoritmi avansati')" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO classes " +
                    "VALUES (1, 1, 'lecture', 'thursday', 12, 0, 13, 50, 1, NULL)" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO classes " +
                    "VALUES (2, 1, 'laboratory', 'friday', 12, 0, 13, 50, 1, 243)" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO classes " +
                    "VALUES (3, 1, 'laboratory', 'tuesday', 18, 0, 19, 50, 1, 242)" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO classes " +
                    "VALUES (4, 2, 'lecture', 'tuesday', 12, 0, 13, 50, 1, NULL)" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO classes " +
                    "VALUES (5, 2, 'laboratory', 'friday', 8, 0, 9, 50, 1, 243)" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO enrollments " +
                    "VALUES (2, 1)" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO enrollments " +
                    "VALUES (2, 2)" +
            "");
            connection.createStatement().execute("" +
                    "INSERT INTO enrollments " +
                    "VALUES (2, 3)" +
            "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
