package ee.home.parkinghouse.dao.impl;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import ee.home.parkinghouse.dao.FeeDao;
import ee.home.parkinghouse.model.Fee;

@Repository
public class FeeDaoImpl implements FeeDao {

    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, Fee> cache = new ConcurrentHashMap<>();

    @Override
    public List<Fee> findAll() {
        return cache.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Fee> findNotInvoiced() {
        return cache.values().stream().filter(fee -> !fee.isInvoiced()).collect(Collectors.toList());
    }

    @Override
    public List<Fee> findByUsername(String username) {
        return cache.values().stream().filter(fee -> fee.getUser().getUsername().equals(username)).collect(Collectors.toList());
    }

    @Override
    public Fee findById(long id) {
        Optional<Fee> fee = cache.values().stream().filter(feeCached -> feeCached.getId() == id).findFirst();
        return fee.get();
    }

    @Override
    public long addFee(Fee fee) {
        fee.setId(counter.incrementAndGet());
        cache.put(fee.getId(), fee);
        return fee.getId();
    }

    @Override
    public long changeFeeById(long id, Fee fee) {
        cache.put(id, fee);
        return id;
    }

}
