package ee.home.parkinghouse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Long> invoice(@RequestParam("username") String username) {

        return ResponseEntity.ok(1L);
    }
}
