package co.cstad.controller;

import co.cstad.model.User;
import co.cstad.service.UserService;
import co.cstad.service.UserServiceImpl;
import co.cstad.storage.Storage;
import co.cstad.util.DownloadSingleton;
import co.cstad.view.UserView;

public class UserController {
    private final UserService userService;
    private final Storage storage;

    public UserController() {
        userService = new UserServiceImpl();
        storage = DownloadSingleton.storage();
    }

    public boolean authenticateUser(String username, String password) {
        return userService.authenticateUser(username, password);
    }

    public void registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.addUser(user);
    }
}
