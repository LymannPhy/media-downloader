package co.cstad.util;

import co.cstad.controller.DownloadController;
import co.cstad.controller.MenuController;
import co.cstad.controller.UserController;
import co.cstad.service.DownloadServiceImpl;
import co.cstad.service.UserServiceImpl;
import co.cstad.storage.Storage;
import co.cstad.view.UserView;
import co.cstad.view.MenuView;

import java.util.Scanner;

public class DownloadSingleton {
    private static Scanner scanner;
    private static DownloadController downloadController;
    private static DownloadServiceImpl downloadServiceImpl;
    private static UserController userController;
    private static MenuController menuController;
    private static UserServiceImpl userServiceImpl;
    private static CategorySeeder categorySeeder;
    private static UserView userView;
    private static MenuView menuView;
    private static Storage storage;
    public static DownloadController downloadController() {
        if(downloadController == null) {
            downloadController = new DownloadController();
        }
        return downloadController;
    }

    public static UserView userView() {
        if(userView == null) {
            userView = new UserView();
        }
        return userView;
    }

    public static MenuView menuView() {
        if(menuView == null) {
            menuView = new MenuView();
        }
        return menuView;
    }

    public static CategorySeeder categorySeeder() {
        if(categorySeeder == null) {
            categorySeeder = new CategorySeeder();
        }
        return categorySeeder;
    }

    public static DownloadServiceImpl downloadServiceImpl() {
        if(downloadServiceImpl == null) {
            downloadServiceImpl = new DownloadServiceImpl();
        }
        return downloadServiceImpl;
    }

    public static UserController userController() {
        if(userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public static UserServiceImpl userServiceImpl() {
        if(userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    public static MenuController menuController() {
        if(menuController == null) {
            menuController = new MenuController();
        }
        return menuController;
    }

    public static Scanner scanner() {
        if(scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static Storage storage() {
        if(storage == null) {
            storage = new Storage();
        }
        return storage;
    }
}
