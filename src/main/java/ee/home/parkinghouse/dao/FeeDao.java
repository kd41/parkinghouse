package ee.home.parkinghouse.dao;

import java.util.List;

import ee.home.parkinghouse.model.Fee;

public interface FeeDao {

    List<Fee> findAll();

    List<Fee> findNotInvoiced();

    List<Fee> findByUsername(String username);

    Fee findById(long id);

    long addFee(Fee fee);

    long changeFeeById(long id, Fee fee);

}
