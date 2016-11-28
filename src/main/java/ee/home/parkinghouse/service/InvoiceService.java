package ee.home.parkinghouse.service;

import java.util.List;

import ee.home.parkinghouse.model.Invoice;
import ee.home.parkinghouse.model.User;

public interface InvoiceService {

    List<Invoice> findByUser(User user);

    long addInvoice(Invoice invoice);

}
