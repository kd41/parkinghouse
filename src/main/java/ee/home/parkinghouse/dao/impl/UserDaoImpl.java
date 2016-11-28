package ee.home.parkinghouse.dao.impl;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import ee.home.parkinghouse.dao.UserDao;
import ee.home.parkinghouse.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    private final AtomicLong counter = new AtomicLong();
    private final Map<String, User> cache = new ConcurrentHashMap<>();

    @Override
    public List<User> findAll() {
        return cache.values().stream().filter(user -> !user.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public long nextUserId() {
        return counter.incrementAndGet();
    }

    @Override
    public User findByUsername(String username) {
        return cache.get(username);
    }

    @Override
    public long addUser(User user) {
        cache.put(user.getUsername(), user);
        return user.getId();
    }

    @Override
    public void changeUserByUsername(String username, User user) {
        cache.put(username, user);
    }

    @Override
    public long deleteUser(User user) {
        user.setDeleted(true);
        return user.getId();
    }

}
