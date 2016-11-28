package ee.home.parkinghouse.service;

import java.util.Date;
import java.util.List;

import ee.home.parkinghouse.model.Fee;

public interface FeeService {

    List<Fee> findAll();

    List<Fee> findNotInvoiced();

    List<Fee> findByUsername(String username);

    Fee findById(long id);

    long addFee(String username, Date startDate, Date endDate);

    long changeFeeById(long id, Fee fee);

}
