package services;

import structure.quizzes.Quiz;
import structure.subjects.Subject;
import structure.users.Professor;
import structure.users.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static AppService service;
    static CSVReader reader;
    static AuditWriter writer;
    static DBService dbService;

    static {
        service = AppService.getInstance();
        reader = CSVReader.getInstance();
        writer = AuditWriter.getInstance();
        try {
            dbService = DBService.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        dbService.loadData(service);
        run();
    }

    public static void listOptions() {
        System.out.println("-1 - Exit");
        System.out.println("0 - Log out");
        System.out.println("1 - Show your timetable (for students)");
        System.out.println("2 - Show subjects that you are enrolled in (for students)");
        System.out.println("3 - Create a new multiple choice quiz for a subject (for professors)");
        System.out.println("4 - Show all quizzes for a subject (for professors)");
    }

    public static void run() {
        service.askLogIn();
        writer.writeToAudit("User logged in");

        System.out.println(new StringBuilder()
                .append("Welcome, ")
                .append(service.getLoggedUser().getFirstName())
                .append(" ")
                .append(service.getLoggedUser().getLastName())
                .append("! You are logged in as a ")
                .append(service.getLoggedUser() instanceof Student ? "student" : "professor")
                .append(" with email ")
                .append(service.getLoggedUser().getEmail())
                .append(".\n")
        );

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("Please enter one of the following options");
            listOptions();
            System.out.print("Enter your option: ");
            option = scanner.nextInt();
            System.out.println();

            switch (option) {
                case (0) -> {
                    service.logOut();
                    System.out.println("Logged out");
                    writer.writeToAudit("User logged out");
                    service.askLogIn();
                    writer.writeToAudit("User logged in");
                }
                case (1) -> {
                    System.out.println(((Student) (service.getLoggedUser())).getTimetable());
                    writer.writeToAudit("Printed user timetable");
                }
                case (2) -> {
                    service.showUserSubjects();
                    writer.writeToAudit("Printed subjects user is enrolled in");
                }
                case (3) -> {
                    if (service.getLoggedUser() instanceof Professor) {
                        Quiz quiz = service.createQuiz();
                        scanner.nextLine();
                        System.out.print("Enter subject for which you want to add the quiz: ");
                        Subject subject = service.getSubjectByName(scanner.nextLine());
                        if (subject == null)
                            System.out.println("Subject not found!");
                        else {
                            subject.addQuiz(quiz);
                        }
                        writer.writeToAudit("Quiz created by a professor");
                    } else {
                        System.out.println("Only professors can see the quizzes");
                    }
                }
                case(4) -> {
                    if (service.getLoggedUser() instanceof Professor) {
                        scanner.nextLine();
                        System.out.println("Enter subject for which you want to see the quizzes");
                        Subject subject = service.getSubjectByName(scanner.nextLine());
                        if (subject == null)
                            System.out.println("Subject not found!");
                        else {
                            for (Quiz quiz : subject.getQuizzes()) {
                                System.out.println(quiz);
                            }
                        }
                        writer.writeToAudit("Printed all quizes for a subject");
                    } else {
                        System.out.println("Only professors can see the quizzes");
                    }
                }
            }
            System.out.println();
        } while (option != -1);

        System.out.println("Goodbye!");
    }
}
