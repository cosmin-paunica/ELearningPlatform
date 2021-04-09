package services;

import structure.quizzes.Quiz;
import structure.subjects.Subject;
import structure.users.Student;

import java.util.Scanner;

public class Main {
    static ConsoleService service = ConsoleService.getInstance();

    public static void main(String[] args) {
        run();
    }

    public static void listOptions() {
        System.out.println("-1 - Exit");
        System.out.println("0 - Log out");
        System.out.println("1 - Show your timetable (for students)");
        System.out.println("2 - Show subjects that you are enrolled in (for students)");
        System.out.println("3 - Create a new multiple choice quiz for a subject (for professors)");
    }

    public static void run() {
        service.addDummyData();

        service.askLogIn();

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

            switch (option) {
                case (0) -> {
                    service.logOut();
                    System.out.println("Logged out");
                    service.askLogIn();
                }
                case (1) -> System.out.println(((Student) (service.getLoggedUser())).getTimetable());
                case (2) -> service.showUserSubjects();
                case (3) -> {
                    Quiz quiz = service.createQuiz();
                    System.out.print("Enter subject for which you want the quiz: ");
                    Subject subject = service.findSubject(scanner.nextLine());
                    if (subject == null)
                        System.out.println("Subject not found!");
                    else {
                        subject.addQuiz(quiz);
                    }
                }
            }
            System.out.println();
        } while (option != -1);

        System.out.println("Goodbye!");
    }
}
