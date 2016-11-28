package ee.home.parkinghouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ee.home.parkinghouse.calculation.InvoiceCalculation;
import ee.home.parkinghouse.dao.InvoiceDao;
import ee.home.parkinghouse.model.Invoice;
import ee.home.parkinghouse.model.User;
import ee.home.parkinghouse.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private InvoiceCalculation invoiceCalculation;

    @Override
    public List<Invoice> findByUser(User user) {
        invoiceCalculation.calculateInvoices();
        return invoiceDao.findByUser(user);
    }

    @Override
    public long addInvoice(Invoice invoice) {
        return invoiceDao.addInvoice(invoice);
    }

}
