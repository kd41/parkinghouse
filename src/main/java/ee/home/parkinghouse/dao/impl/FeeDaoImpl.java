package ee.home.parkinghouse.dao.impl;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import ee.home.parkinghouse.dao.FeeDao;
import ee.home.parkinghouse.model.Fee;

@Repository
public class FeeDaoImpl implements FeeDao {

    private final AtomicLong counter = new AtomicLong();
    private final List<Fee> cache = Collections.synchronizedList(new ArrayList<>());

    @Override
    public List<Fee> findAll() {
        return cache;
    }

    @Override
    public List<Fee> findByUsername(String username) {
        return cache.stream().filter(fee -> fee.getUser().getUsername() == username).collect(Collectors.toList());
    }

    @Override
    public long addFee(Fee fee) {
        fee.setId(counter.incrementAndGet());
        cache.add(fee);
        return fee.getId();
    }

    @Override
    public long changeFeeById(long id, Fee fee) {
        cache.add(fee);
        return id;
    }

}
