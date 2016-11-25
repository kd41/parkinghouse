package ee.home.parkinghouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import ee.home.parkinghouse.exception.BadRequestException;
import ee.home.parkinghouse.model.Fee;
import ee.home.parkinghouse.service.FeeService;
import ee.home.parkinghouse.util.DateUtil;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Fee>> all() {
        return ResponseEntity.ok(feeService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public ResponseEntity<Collection<Fee>> findByUsername(@PathVariable String username) {
        checkUsername(username);
        List<Fee> fees = feeService.findByUsername(username);
        return ResponseEntity.ok(fees);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{username}")
    public ResponseEntity<Long> add(@PathVariable String username, @RequestParam("start") @DateTimeFormat(pattern = DateUtil.DATE_FORMAT) Date start,
            @RequestParam("end") @DateTimeFormat(pattern = DateUtil.DATE_FORMAT) Date end) {
        checkUsername(username);
        checkDates(start, end);
        return ResponseEntity.ok(feeService.addFee(username, start, end));
    }

    private void checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new BadRequestException();
        }
    }

    private void checkDates(Date start, Date end) {
        if (start.after(end)) {
            throw new BadRequestException();
        }
    }
}
