package co.cstad.view;

import co.cstad.controller.UserController;
import co.cstad.model.User;
import co.cstad.storage.Storage;
import co.cstad.util.DownloadSingleton;

import java.util.Scanner;

public class UserView {
    private final UserController userController;
    private final MenuView menuView;
    private final Storage storage;
    public UserView() {
        userController = DownloadSingleton.userController();
        menuView = DownloadSingleton.menuView();
        storage = DownloadSingleton.storage();
    }

    public void showLoginMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (userController.authenticateUser(username, password)) {
            System.out.println("Login successful!");
            menuView.showMenuView();
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
