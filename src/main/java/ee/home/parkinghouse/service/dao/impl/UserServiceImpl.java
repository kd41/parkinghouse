package ee.home.parkinghouse.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ee.home.parkinghouse.dao.UserDao;
import ee.home.parkinghouse.model.User;
import ee.home.parkinghouse.service.dao.UserService;
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
    public long addUser(String username) {
        if (userDao.findByUsername(username) != null) {
            throw new AlreadyExistsException();
        }
        User user = new User(userDao.nextUserId(), username);
        return userDao.addUser(user);
    }

    @Override
    public void changeUsername(String username, String newUsername) {
        checkExists(username);
        User user = userDao.findByUsername(username);
        user.setUsername(newUsername);
        userDao.changeUserByUsername(username, user);
    }

    @Override
    public long deleteUser(String username) {
        checkExists(username);
        return userDao.deleteUser(username);
    }

    private void checkExists(String username) {
        if (userDao.findByUsername(username) == null) {
            throw new NotFoundException("User not found. Username=" + username);
        }
    }
}
