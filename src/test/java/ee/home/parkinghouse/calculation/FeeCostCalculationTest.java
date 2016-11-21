package ee.home.parkinghouse.calculation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ee.home.parkinghouse.application.ParkinghouseConfiguration;
import ee.home.parkinghouse.application.ParkinghouseProperties;
import ee.home.parkinghouse.model.Fee;
import ee.home.parkinghouse.model.User.CustomerType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { ParkinghouseProperties.class, ParkinghouseConfiguration.class, FeeCostCalculation.class }, loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class FeeCostCalculationTest {

    @Autowired
    private FeeCostCalculation feeCostCalculation;

    @Test
    public void getRegularCostTest1() throws ParseException {
        Date start = parseDate("21.11.2016 08:12");
        Date end = parseDate("21.11.2016 10:45");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.REGULAR);

        assertEquals("Fee parts count should be 1", 1, fee.getParts().size());
        Fee.Part part = fee.getParts().get(0);
        assertEquals("Fee part cost should be 150", 150L, part.getCost());
        assertEquals("Fee parts count should be 6", 6, part.getCount());
        assertEquals("Fee total cost should be 900", 900, fee.getTotalCost());
    }

    @Test
    public void getRegularCostTest2() throws ParseException {
        Date start = parseDate("21.11.2016 19:40");
        Date end = parseDate("21.11.2016 20:35");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.REGULAR);

        assertEquals("Fees count should be 1", 1, fee.getParts().size());
        Fee.Part part = fee.getParts().get(0);
        assertEquals("Fee part cost should be 100", 100L, part.getCost());
        assertEquals("Fee parts count should be 2", 2, part.getCount());
        assertEquals("Fee total cost should be 200", 200, fee.getTotalCost());
    }

    @Test
    public void getRegularCostTest3() throws ParseException {
        Date start = parseDate("21.11.2016 08:00");
        Date end = parseDate("21.11.2016 08:29");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.REGULAR);

        assertEquals("Fees count should be 1", 1, fee.getParts().size());
        Fee.Part part = fee.getParts().get(0);
        assertEquals("Fee part cost should be 150", 150L, part.getCost());
        assertEquals("Fee parts count should be 1", 1, part.getCount());
        assertEquals("Fee total cost should be 150", 150, fee.getTotalCost());
    }

    @Test
    public void getRegularCostTest4() throws ParseException {
        Date start = parseDate("21.11.2016 08:00");
        Date end = parseDate("21.11.2016 08:30");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.REGULAR);

        assertEquals("Fees count should be 1", 1, fee.getParts().size());
        Fee.Part part = fee.getParts().get(0);
        assertEquals("Fee part cost should be 150", 150L, part.getCost());
        assertEquals("Fee parts count should be 2", 2, part.getCount());
        assertEquals("Fee total cost should be 300", 300, fee.getTotalCost());
    }

    @Test
    public void getPremiumCostTest1() throws ParseException {
        Date start = parseDate("21.11.2016 08:12");
        Date end = parseDate("21.11.2016 10:45");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.PREMIUM);

        assertEquals("Fees count should be 1", 1, fee.getParts().size());
        Fee.Part part = fee.getParts().get(0);
        assertEquals("Fee part cost should be 100", 100L, part.getCost());
        assertEquals("Fee parts count should be 6", 6, part.getCount());
        assertEquals("Fee total cost should be 600", 600, fee.getTotalCost());
    }

    @Test
    public void getPremiumCostTest2() throws ParseException {
        Date start = parseDate("21.11.2016 07:02");
        Date end = parseDate("21.11.2016 11:56");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.PREMIUM);

        assertEquals("Fees count should be 1", 1, fee.getParts().size());
        Fee.Part part = fee.getParts().get(0);
        assertEquals("Fee part cost should be 100", 100L, part.getCost());
        assertEquals("Fee parts count should be 10", 10, part.getCount());
        assertEquals("Fee total cost should be 1000", 1000, fee.getTotalCost());
    }

    @Test
    public void getPremiumCostTest3() throws ParseException {
        Date start = parseDate("21.11.2016 22:10");
        Date end = parseDate("21.11.2016 22:35");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.PREMIUM);

        assertEquals("Fees count should be 1", 1, fee.getParts().size());
        Fee.Part part = fee.getParts().get(0);
        assertEquals("Fee part cost should be 75", 75L, part.getCost());
        assertEquals("Fee parts count should be 1", 1, part.getCount());
        assertEquals("Fee total cost should be 75", 75, fee.getTotalCost());
    }

    @Test
    public void getPremiumCostTest4() throws ParseException {
        Date start = parseDate("21.11.2016 19:40");
        Date end = parseDate("21.11.2016 20:35");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.PREMIUM);

        assertEquals("Fees count should be 1", 1, fee.getParts().size());
        Fee.Part part = fee.getParts().get(0);
        assertEquals("Fee part cost should be 75", 75L, part.getCost());
        assertEquals("Fee parts count should be 2", 2, part.getCount());
        assertEquals("Fee total cost should be 150", 150, fee.getTotalCost());
    }

    @Test
    public void lessThanOneMinuteTest() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date start = format.parse("21.11.2016 19:40:32");
        Date end = format.parse("21.11.2016 19:40:47");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.PREMIUM);

        assertEquals("Fees count should be 1", 1, fee.getParts().size());
    }

    @Test
    public void lessThanOneMinuteTest1() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date start = format.parse("21.11.2016 19:40:32");
        Date end = format.parse("21.11.2016 19:41:31");
        Fee fee = feeCostCalculation.getFees(start, end, CustomerType.PREMIUM);

        assertEquals("Fees count should be 1", 1, fee.getParts().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void startEndTest() throws ParseException {
        Date start = parseDate("21.11.2016 12:34");
        Date end = parseDate("21.11.2016 11:01");
        feeCostCalculation.getFees(start, end, CustomerType.PREMIUM);
    }

    private Date parseDate(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return format.parse(time);
    }

}
