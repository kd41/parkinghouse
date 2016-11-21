package ee.home.parkinghouse.dao;

import java.util.List;

import ee.home.parkinghouse.model.User;

public interface UserDao {

    List<User> findAll();

    long nextUserId();

    User findByUsername(String username);

    long addUser(User user);

    void changeUserByUsername(String username, User user);

    long deleteUser(String username);
}
