package ee.home.parkinghouse.dao.impl;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import ee.home.parkinghouse.dao.InvoiceDao;
import ee.home.parkinghouse.model.Invoice;
import ee.home.parkinghouse.model.User;

@Repository
public class InvoiceDaoImpl implements InvoiceDao {

    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, Invoice> cache = new ConcurrentHashMap<>();

    @Override
    public List<Invoice> findByUser(User user) {
        return cache.values().stream().filter(invoice -> invoice.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public long addInvoice(Invoice invoice) {
        invoice.setId(counter.incrementAndGet());
        cache.put(invoice.getId(), invoice);
        return invoice.getId();
    }

    @Override
    public long changeInvoiceById(long id, Invoice invoice) {
        cache.put(id, invoice);
        return invoice.getId();
    }

}
