package services;

import structure.users.Professor;
import structure.users.Student;
import structure.users.TeachingDegree;
import structure.users.User;

import java.util.ArrayList;
import java.util.Scanner;

public final class ConsoleService {
    private static ConsoleService instance = null;
    private User loggedUser;
    private ArrayList<User> users;

    public static ConsoleService getInstance() {
        if (ConsoleService.instance == null)
            ConsoleService.instance = new ConsoleService();
        return ConsoleService.instance;
    }

    private ConsoleService() {
        this.loggedUser = null;
        this.users = new ArrayList<>();
    }

    private void addDummyData() {
        this.users.add(new Student("mihai.george@s.unibuc.ro", "George", "Mihai", 2));
        this.users.add(new Professor("bogdan.mihailescu@unibuc.ro", "Mihailescu", "Bogdan", TeachingDegree.LECTURER));
    }

    private User searchUser(String email, String password) {
        for (User user : this.users) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    public void logIn(String email, String password) {
        // password will be queried from csv file / database
        this.loggedUser = this.searchUser(email, password);
    }

    public void logOut() {
        this.loggedUser = null;
    }

    public void run() {
        this.addDummyData();
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

        System.out.println(new StringBuilder()
                .append("Welcome, ")
                .append(this.loggedUser.getFirstName())
                .append(" ")
                .append(this.loggedUser.getLastName())
                .append("! You are logged in as a ")
                .append(this.loggedUser instanceof Student ? "student" : "professor")
                .append(" with email ")
                .append(this.loggedUser.getEmail())
                .append("\n")
                .toString()
        );
    }
}
