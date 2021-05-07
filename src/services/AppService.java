package services;

import structure.classes.Laboratory;
import structure.classes.Lecture;
import structure.quizzes.MultipleChoiceAnswer;
import structure.quizzes.MultipleChoiceQuestion;
import structure.quizzes.Quiz;
import structure.subjects.Subject;
import structure.users.Professor;
import structure.users.Student;
import structure.users.TeachingDegree;
import structure.users.User;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public final class AppService {
    private static AppService instance = null;

    private User loggedUser;
    private ArrayList<User> users;
    private ArrayList<Subject> subjects;
    private PasswordHashService hashService;

    private AppService() {
        this.loggedUser = null;
        this.users = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.hashService = PasswordHashService.getInstance();
    }

    public static AppService getInstance() {
        if (AppService.instance == null)
            AppService.instance = new AppService();
        return AppService.instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    private ArrayList<Subject> createDummySubjects() {
        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(
                new Subject(1, "Programare avansata pe obiecte")
                .addClass(new Lecture(DayOfWeek.THURSDAY, LocalTime.of(12, 0), LocalTime.of(13, 50), (Professor)this.users.get(0)))
                .addClass(new Laboratory(DayOfWeek.FRIDAY, LocalTime.of(12, 0), LocalTime.of(13, 50), (Professor)this.users.get(0), 243))
                .addClass(new Laboratory(DayOfWeek.TUESDAY, LocalTime.of(18, 0), LocalTime.of(19, 50), (Professor)this.users.get(0), 242))
        );
        subjects.add(
                new Subject(2, "Inteligenta artificiala")
                .addClass(new Lecture(DayOfWeek.TUESDAY, LocalTime.of(12, 0), LocalTime.of(13, 50), (Professor)this.users.get(0)))
                .addClass(new Laboratory(DayOfWeek.FRIDAY, LocalTime.of(8, 0), LocalTime.of(9, 50), (Professor)this.users.get(0), 243))
        );
        return subjects;
    }

    public void addDummyData() {
        this.users.add(new Professor(
                1, "bogdan.mihailescu@unibuc.ro", "Mihailescu", "Bogdan", TeachingDegree.LECTURER, "parolaprofesor"
        ));
        this.subjects = this.createDummySubjects();
        this.users.add(new Student(
                2, "mihai.george@s.unibuc.ro", "George", "Mihai", 2, "parolastudent"
        ));
        for (Subject subject : subjects)
            ((Student)(this.users.get(1))).enroll(subject);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public User getUserById(int id) {
        for (User user : this.users)
            if (user.getId() == id)
                return user;
        return null;
    }

    public Subject getSubjectById(int id) {
        for (Subject subject : subjects)
            if (subject.getId() == id)
                return subject;
        return null;
    }

    public void logIn(String email, String password) {
        for (User user : this.users) {
            if (user.getEmail().equals(email) && hashService.verify(password, user.getHashedPassword()))
                this.loggedUser = user;
        }
    }

    public void logOut() {
        this.loggedUser = null;
    }

    public void askLogIn() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            this.logIn(email, password);
            if (this.loggedUser == null)
                System.out.println("Invalid data");
        } while (this.loggedUser == null);
    }

    public void showUserSubjects() {
        if (loggedUser instanceof Student) {
            for (Subject subject : ((Student) loggedUser).getEnrolledSubjects())
                System.out.println(subject.getName());
        } else {

        }
    }

    public Quiz createQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter start and finish date and time for the new quiz:");
        Quiz quiz = new Quiz(
                LocalDateTime.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()),
                LocalDateTime.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt())
        );
        System.out.println("Enter number of questions");
        int numQuestions = scanner.nextInt();
        for (int i = 0; i < numQuestions; i++) {
            scanner.nextLine();
            System.out.println("Enter question text:");
            String questionText = scanner.nextLine();
            MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionText);
            System.out.println("Enter number of answer options: ");
            int numAnswerOptions = scanner.nextInt();
            for (int j = 0; j < numAnswerOptions; j++) {
                scanner.nextLine();
                System.out.println("Enter answer option text and 1 for true / 0 for false");
                String answerText = scanner.nextLine();
                boolean correctness = scanner.nextInt() == 1;
                question.addAnswerOption(new MultipleChoiceAnswer(answerText, correctness));
            }
            quiz.addQuestion(question);
        }
        return quiz;
    }

    public Subject findSubject(String subjectName) {
        for (Subject subject : subjects)
            if (subject.getName().equals(subjectName))
                return subject;
        return null;
    }

    public DayOfWeek stringToDayOfWeek(String string) {
        if (string.equals("monday"))
            return DayOfWeek.MONDAY;
        if (string.equals("tuesday"))
            return DayOfWeek.TUESDAY;
        if (string.equals("wednesday"))
            return DayOfWeek.WEDNESDAY;
        if (string.equals("thursday"))
            return DayOfWeek.THURSDAY;
        if (string.equals("friday"))
            return DayOfWeek.FRIDAY;
        if (string.equals("saturday"))
            return DayOfWeek.SATURDAY;
        if (string.equals("sunday"))
            return DayOfWeek.SUNDAY;
        return null;
    }
}
