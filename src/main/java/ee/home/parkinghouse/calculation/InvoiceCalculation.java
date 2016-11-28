package ee.home.parkinghouse.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ee.home.parkinghouse.model.Fee;
import ee.home.parkinghouse.model.Invoice;
import ee.home.parkinghouse.service.FeeService;
import ee.home.parkinghouse.service.InvoiceService;
import ee.home.parkinghouse.util.DateUtil;

@Component
public class InvoiceCalculation {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private FeeService feeService;

    public synchronized void calculateInvoices() {
        List<Fee> fees = feeService.findNotInvoiced();
        List<Invoice> newInvoices = new ArrayList<>();
        fees.forEach(fee -> {
            fee.setInvoiced(true);
            Invoice invoice = null;
            Optional<Invoice> invoiceOptional = newInvoices.stream().filter(item -> item.getUser().equals(fee.getUser())
                    && DateUtil.equalMonths(fee.getStart(), item.getDate()) && DateUtil.equalYears(fee.getStart(), item.getDate())).findFirst();
            if (!invoiceOptional.isPresent()) {
                invoice = new Invoice();
                invoice.setDate(DateUtil.now());
                invoice.setUser(fee.getUser());
                newInvoices.add(invoice);
            } else {
                invoice = invoiceOptional.get();
            }
            invoice.getFees().add(fee);
            feeService.changeFeeById(fee.getId(), fee);
        });

        newInvoices.forEach(invoice -> invoiceService.addInvoice(invoice));
    }
}
