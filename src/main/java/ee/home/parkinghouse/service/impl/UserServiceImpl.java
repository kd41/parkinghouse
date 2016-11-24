package ee.home.parkinghouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ee.home.parkinghouse.dao.UserDao;
import ee.home.parkinghouse.model.User;
import ee.home.parkinghouse.model.User.CustomerType;
import ee.home.parkinghouse.service.UserService;
import ee.home.parkinghouse.service.exception.AlreadyExistsException;
import ee.home.parkinghouse.service.exception.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUsername(String username) {
        checkExists(username);
        return userDao.findByUsername(username);
    }

    @Override
    public long addUser(String username, CustomerType type) {
        if (userDao.findByUsername(username) != null) {
            throw new AlreadyExistsException();
        }
        User user = new User(userDao.nextUserId(), username);
        user.setCustomerType(type);
        return userDao.addUser(user);
    }

    @Override
    public void changeUsername(String username, String newUsername) {
        checkExists(username);
        User user = userDao.findByUsername(username);
        checkDeleted(user);
        user.setUsername(newUsername);
        userDao.changeUserByUsername(username, user);
    }

    @Override
    public long deleteUser(String username) {
        checkExists(username);
        User user = userDao.findByUsername(username);
        checkDeleted(user);
        return userDao.deleteUser(user);
    }

    private void checkExists(String username) {
        if (userDao.findByUsername(username) == null) {
            throw new NotFoundException("User not found. Username=" + username);
        }
    }

    private void checkDeleted(User user) {
        if (user.isDeleted()) {
            throw new NotFoundException("User already deleted. Username=" + user.getUsername());
        }
    }
}
