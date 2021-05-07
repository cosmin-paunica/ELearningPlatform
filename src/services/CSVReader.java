package services;

import structure.classes.Class;
import structure.classes.Laboratory;
import structure.classes.Lecture;
import structure.classes.Seminar;
import structure.subjects.Subject;
import structure.users.Professor;
import structure.users.Student;
import structure.users.TeachingDegree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Locale;

public class CSVReader {
    private static CSVReader instance = null;

    private final String csvDirPath = "D:\\Facultate\\Anul II\\Semestrul 2\\Programare avansata pe obiecte\\Laborator\\ELearningPlatform\\res\\csv\\";

    private CSVReader() {}

    public static CSVReader getInstance() {
        if (CSVReader.instance == null) {
            CSVReader.instance = new CSVReader();
        }
        return CSVReader.instance;
    }

    private BufferedReader getBufferedReader(String path) {
        try {
            FileReader finUsers = new FileReader(path);
            return new BufferedReader(finUsers);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadUsers(AppService service) throws FileNotFoundException {
        BufferedReader userReader = this.getBufferedReader(this.csvDirPath + "users.csv");
        try {
            String row = userReader.readLine();    // line of titles
            while ((row = userReader.readLine()) != null) {
                String[] userArray = row.split(",");
                if (userArray[1].toLowerCase(Locale.ROOT).equals("professor")) {
                    TeachingDegree degree;
                    if (userArray[5].toLowerCase(Locale.ROOT).equals("lecturer"))
                        degree = TeachingDegree.LECTURER;
                    else if (userArray[5].toLowerCase(Locale.ROOT).equals("assistant"))
                        degree = TeachingDegree.ASSISTANT;
                    else
                        degree = TeachingDegree.PROFESSOR;
                    service.addUser(new Professor(Integer.parseInt(userArray[0]), userArray[2], userArray[3], userArray[4], degree, userArray[6]));
                } else if (userArray[1].toLowerCase(Locale.ROOT).equals("student")) {
                    service.addUser(new Student(Integer.parseInt(userArray[0]), userArray[2], userArray[3], userArray[4], Integer.parseInt(userArray[5]), userArray[6]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSubjects(AppService service) {
        BufferedReader subjectReader = this.getBufferedReader(this.csvDirPath + "subjects.csv");
        try {
            String row = subjectReader.readLine(); // line of titles
            while ((row = subjectReader.readLine()) != null) {
                String[] subjectArray = row.split(",");
                service.addSubject(new Subject(Integer.parseInt(subjectArray[0]), subjectArray[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadClasses(AppService service) {
        BufferedReader classReader = this.getBufferedReader(this.csvDirPath + "classes.csv");
        try {
            String row = classReader.readLine();
            while ((row = classReader.readLine()) != null) {
                String[] classArray = row.split(",");
                Subject subject = service.getSubjectById(Integer.parseInt(classArray[1]));
                String classType = classArray[2].toLowerCase();
                DayOfWeek dayOfWeek = service.stringToDayOfWeek(classArray[3].toLowerCase());
                Class newClass = null;
                if (classType.equals("lecture")) {
                    newClass = new Lecture(
                            dayOfWeek,
                            LocalTime.of(Integer.parseInt(classArray[4]), Integer.parseInt(classArray[5])),
                            LocalTime.of(Integer.parseInt(classArray[6]), Integer.parseInt(classArray[7])),
                            (Professor)service.getUserById(Integer.parseInt(classArray[8]))
                    );
                } else if (classType.equals("seminar")) {
                    newClass = new Seminar(
                            dayOfWeek,
                            LocalTime.of(Integer.parseInt(classArray[4]), Integer.parseInt(classArray[5])),
                            LocalTime.of(Integer.parseInt(classArray[6]), Integer.parseInt(classArray[7])),
                            (Professor)service.getUserById(Integer.parseInt(classArray[8])),
                            Integer.parseInt(classArray[9])
                    );
                } else if (classType.equals("laboratory")) {
                    newClass = new Laboratory(
                            dayOfWeek,
                            LocalTime.of(Integer.parseInt(classArray[4]), Integer.parseInt(classArray[5])),
                            LocalTime.of(Integer.parseInt(classArray[6]), Integer.parseInt(classArray[7])),
                            (Professor)service.getUserById(Integer.parseInt(classArray[8])),
                            Integer.parseInt(classArray[9])
                    );
                }
                subject.addClass(newClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEnrollments(AppService service) {
        BufferedReader enrollmentReader = this.getBufferedReader(this.csvDirPath + "enrollments.csv");
        try {
            String row = enrollmentReader.readLine();
            while ((row = enrollmentReader.readLine()) != null) {
                String[] enrollmentArray = row.split(",");
                Student student = ((Student)service.getUserById(Integer.parseInt(enrollmentArray[0])));
                Subject subject = service.getSubjectById(Integer.parseInt(enrollmentArray[1]));
                student.enroll(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadQuizzes(AppService service) {

    }

    public void loadData(AppService service) throws FileNotFoundException {
        loadUsers(service);
        loadSubjects(service);
        loadClasses(service);
        loadEnrollments(service);
        loadQuizzes(service);
    }
}
