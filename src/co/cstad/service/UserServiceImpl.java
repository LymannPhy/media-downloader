package co.cstad.service;

import co.cstad.dao.UserDao;
import co.cstad.model.User;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        User user = userDao.getUserByUsername(username);

        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
