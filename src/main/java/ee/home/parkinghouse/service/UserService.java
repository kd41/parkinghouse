package ee.home.parkinghouse.service;

import java.util.List;

import ee.home.parkinghouse.model.User;
import ee.home.parkinghouse.model.User.CustomerType;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    long addUser(String username, CustomerType type);

    void changeUsername(String username, String newUsername);

    long deleteUser(String username);
}
