package sk.fmfi.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import sk.fmfi.model.Fee;
import sk.fmfi.repository.FeeRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class FeeServiceBean implements FeeService{

    private final FeeRepository feeRepository;
    private final Logger logger = Logger.getLogger(FeeServiceBean.class.getName());

    @ConfigProperty(name = "minimal.fee.limit")
    private int minimalFeeLimit;

    @Inject
    public FeeServiceBean(FeeRepository feeRepository){
        this.feeRepository = feeRepository;
    }

    private BigDecimal calculateTransactionFee(BigDecimal transactionAmount) {
        if (transactionAmount.compareTo(new BigDecimal(minimalFeeLimit)) > 0) {
            return transactionAmount.subtract(new BigDecimal(2));
        }
        return new BigDecimal(transactionAmount.longValue() - 0.01);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Fee createFee(String transactionId, String acno, BigDecimal transactionAmount) {
        logger.log(Level.ALL, "Creating fee with transaction id " + transactionId
                                    + "; acno " + acno
                                    + "; amount" + transactionAmount);
        Fee fee = new Fee();
        fee.setAcno(acno);
        fee.setTransactionId(transactionId);
        fee.setAmount(calculateTransactionFee(transactionAmount));
        fee.setCreationDate(LocalDateTime.now());
        feeRepository.persist(fee);
        logger.log(Level.ALL, "Fee created");
        return fee;
    }

    @Override
    @Transactional(Transactional.TxType.NEVER)
    public List<Fee> getAllFees() {
        logger.log(Level.ALL, "Getting all fees");
        return feeRepository.findAll().list();
    }

    @Override
    @Transactional(Transactional.TxType.NEVER)
    public List<Fee> getFeesForAcno(String acno) {
        logger.log(Level.ALL, "Getting fees for acno " + acno);
        return feeRepository.listForAcno(acno);
    }
}
