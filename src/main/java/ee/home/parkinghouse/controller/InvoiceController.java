package ee.home.parkinghouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import ee.home.parkinghouse.exception.BadRequestException;
import ee.home.parkinghouse.model.Invoice;
import ee.home.parkinghouse.model.User;
import ee.home.parkinghouse.service.InvoiceService;
import ee.home.parkinghouse.service.UserService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private UserService userService;

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> invoice(@RequestParam("username") String username) {
        checkUsername(username);
        User user = userService.findByUsername(username);

        List<Invoice> invoices = invoiceService.findByUser(user);
        return ResponseEntity.ok(invoices);
    }

    private void checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new BadRequestException();
        }
    }
}
