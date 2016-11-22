package ee.home.parkinghouse.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import ee.home.parkinghouse.dao.FeeDao;
import ee.home.parkinghouse.model.Fee;
import ee.home.parkinghouse.model.User;
import ee.home.parkinghouse.model.User.CustomerType;
import ee.home.parkinghouse.service.dao.FeeService;
import ee.home.parkinghouse.service.dao.UserService;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private FeeDao feeDao;

    @Autowired
    private UserService userService;

    @Override
    public List<Fee> findAll() {
        return feeDao.findAll();
    }

    @Override
    public List<Fee> findByUsername(String username) {
        return feeDao.findByUsername(username);
    }

    @Override
    public long addFee(String username, Date startDate, Date endDate) {
        User user = userService.findByUsername(username);
        if (user == null) {
            userService.addUser(username, CustomerType.REGULAR);
            user = userService.findByUsername(username);
        }
        Fee fee = new Fee();
        fee.setStart(startDate);
        fee.setEnd(endDate);
        fee.setUser(user);

        long feeId = feeDao.addFee(fee);
        return feeId;
    }

}
