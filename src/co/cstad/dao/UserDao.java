package co.cstad.dao;

import co.cstad.model.User;

public interface UserDao {
    User getUserByUsername(String username);
    void addUser(User user);
}

