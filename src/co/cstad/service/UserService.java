package co.cstad.service;

import co.cstad.model.User;

public interface UserService {
    boolean authenticateUser(String username, String password);
    void addUser(User user);
//    User register(User user);
//    User login(User user);
}
