package co.cstad.view;

import co.cstad.controller.UserController;

import java.util.Scanner;

public class LoginView {
    private UserController userController;

    public LoginView(UserController userController) {
        this.userController = userController;
    }

    public void showLoginMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (userController.authenticateUser(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials. Login failed.");
        }
    }

    public void showRegistrationMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        userController.registerUser(username, password);
        System.out.println("User registered successfully!");
    }
}
