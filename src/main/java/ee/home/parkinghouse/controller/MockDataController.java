package ee.home.parkinghouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import ee.home.parkinghouse.model.User.CustomerType;
import ee.home.parkinghouse.service.dao.FeeService;
import ee.home.parkinghouse.service.dao.UserService;
import ee.home.parkinghouse.util.DateUtil;

@RestController
@RequestMapping("/api/mock")
public class MockDataController {
    private static final String USER_SIMON = "Simon";
    private static final String USER_RUFUS = "Rufus";
    private static final String USER_GRAZIANO = "Graziano";
    private static final String DATE_START = "22.11.2016_12:00";
    private static final String DATE_END = "22.11.2016_13:59";

    @Autowired
    private UserService userService;

    @Autowired
    private FeeService feeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Void> insertMocks() {

        userService.addUser(USER_SIMON, CustomerType.REGULAR);
        userService.addUser(USER_RUFUS, CustomerType.REGULAR);
        userService.addUser(USER_GRAZIANO, CustomerType.PREMIUM);

        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        try {
            feeService.addFee(USER_SIMON, format.parse(DATE_START), format.parse(DATE_END));
            feeService.addFee(USER_RUFUS, format.parse(DATE_START), format.parse(DATE_START));
            feeService.addFee(USER_GRAZIANO, format.parse(DATE_START), format.parse(DATE_END));
        } catch (ParseException e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
