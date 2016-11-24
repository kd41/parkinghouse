package ee.home.parkinghouse.service;

import java.util.Date;
import java.util.List;

import ee.home.parkinghouse.model.Fee;

public interface FeeService {

    List<Fee> findAll();

    List<Fee> findByUsername(String username);

    long addFee(String username, Date startDate, Date endDate);

}
