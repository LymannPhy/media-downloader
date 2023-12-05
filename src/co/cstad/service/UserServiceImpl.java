package co.cstad.service;

import co.cstad.dao.UserDao;
import co.cstad.dao.UserDaoImpl;
import co.cstad.model.User;
import co.cstad.storage.Storage;
import co.cstad.util.DownloadSingleton;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Storage storage;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
        storage = DownloadSingleton.storage();
    }

//    @Override
//    public User register(User user) {
//        return userDao.register(user);
//    }
//
//    @Override
//    public User login(User user) {
//        User response = userDao.login(user);
//        if(response.getId() != null) {
//            storage.setId(response.getId());
//        }
//        return user;
//    }

    @Override
    public boolean authenticateUser(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if(user != null) {
            storage.setId(user.getId());
        }
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
