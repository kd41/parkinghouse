package ee.home.parkinghouse.dao;

import java.util.List;

import ee.home.parkinghouse.model.Fee;

public interface FeeDao {

    List<Fee> findAll();

    List<Fee> findByUsername(String username);

    long addFee(Fee fee);

    long changeFeeById(long id, Fee fee);

}
