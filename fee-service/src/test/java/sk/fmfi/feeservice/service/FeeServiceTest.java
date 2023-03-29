package sk.fmfi.feeservice.service;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.util.Assert;
import sk.fmfi.model.Fee;
import sk.fmfi.repository.FeeRepository;
import sk.fmfi.service.FeeService;

import javax.inject.Inject;
import java.math.BigDecimal;

@QuarkusTest
public class FeeServiceTest {

    private FeeService feeService;

    @InjectMock
    private FeeRepository feeRepository;

    @Inject
    public FeeServiceTest(FeeService feeService) {
        this.feeService = feeService;
    }

    @Test
    public void testGreater10000() {
        Fee fee = feeService.createFee("id", "acno", new BigDecimal(10002));
        Assert.equals(new BigDecimal(10000), fee.getAmount());
    }

    @Test
    public void testLess10000() {
        Fee fee = feeService.createFee("id2", "acno", new BigDecimal(10));
        Assert.equals(new BigDecimal(9.99), fee.getAmount());
    }


}
