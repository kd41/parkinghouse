package ee.home.parkinghouse.service.dao;

import java.util.List;

import ee.home.parkinghouse.model.User;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    long addUser(String username);

    void changeUsername(String username, String newUsername);

    long deleteUser(String username);
}
