package co.cstad.controller;

import co.cstad.model.User;
import co.cstad.service.UserService;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
