package ee.home.parkinghouse.dao;

import java.util.List;

import ee.home.parkinghouse.model.Invoice;
import ee.home.parkinghouse.model.User;

public interface InvoiceDao {

    List<Invoice> findByUser(User user);

    long addInvoice(Invoice invoice);

    public long changeInvoiceById(long id, Invoice invoice);
}
