package ee.home.parkinghouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import ee.home.parkinghouse.calculation.FeeCostCalculation;
import ee.home.parkinghouse.dao.FeeDao;
import ee.home.parkinghouse.exception.InternalException;
import ee.home.parkinghouse.exception.NotFoundException;
import ee.home.parkinghouse.model.Fee;
import ee.home.parkinghouse.model.User;
import ee.home.parkinghouse.model.User.CustomerType;
import ee.home.parkinghouse.service.FeeService;
import ee.home.parkinghouse.service.UserService;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private FeeDao feeDao;

    @Autowired
    private UserService userService;

    @Autowired
    private FeeCostCalculation feeCostCalculation;

    @Override
    public List<Fee> findAll() {
        return feeDao.findAll();
    }

    @Override
    public List<Fee> findNotInvoiced() {
        return feeDao.findNotInvoiced();
    }

    @Override
    public List<Fee> findByUsername(String username) {
        return feeDao.findByUsername(username);
    }

    @Override
    public Fee findById(long id) {
        checkExists(id);
        return feeDao.findById(id);
    }

    @Override
    public long addFee(String username, Date startDate, Date endDate) {
        User user;
        try {
            user = userService.findByUsername(username);
        } catch (NotFoundException e) {
            userService.addUser(username, CustomerType.REGULAR);
            user = userService.findByUsername(username);

        }
        Fee fee = feeCostCalculation.getFee(startDate, endDate, user.getCustomerType());
        fee.setStart(startDate);
        fee.setEnd(endDate);
        fee.setUser(user);

        long feeId = feeDao.addFee(fee);
        return feeId;
    }

    @Override
    public long changeFeeById(long id, Fee fee) {
        checkExists(id);
        if (id != fee.getId()) {
            throw new InternalException("Fee id and id are not equal.Fee id=" + fee.getId() + ", id=" + id);
        }
        feeDao.changeFeeById(id, fee);
        return id;
    }

    private void checkExists(long id) {
        Fee fee = feeDao.findById(id);
        if (fee == null) {
            throw new NotFoundException("Fee not found. Fee id=" + id);
        }
    }

}
