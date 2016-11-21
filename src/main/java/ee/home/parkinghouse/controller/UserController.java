package ee.home.parkinghouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import ee.home.parkinghouse.model.User;
import ee.home.parkinghouse.service.dao.UserService;
import ee.home.parkinghouse.service.exception.BadRequestException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> all() {
        return ResponseEntity.ok(userService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{username}")
    public ResponseEntity<Long> add(@PathVariable String username) {
        checkUsername(username);
        return ResponseEntity.ok(userService.addUser(username));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{username}")
    public ResponseEntity<Long> change(@PathVariable String username, @RequestBody String newUsername) {
        checkUsername(username);
        checkUsername(newUsername);
        userService.changeUsername(username, newUsername);

        return ResponseEntity.ok(userService.findByUsername(newUsername).getId());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
    public ResponseEntity<Long> delete(@PathVariable String username) {
        checkUsername(username);
        return ResponseEntity.ok(userService.deleteUser(username));
    }

    private void checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new BadRequestException();
        }
    }
}
