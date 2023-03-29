package sk.fmfi.service;

import sk.fmfi.model.Fee;
import sk.fmfi.repository.FeeRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@RequestScoped
public class FeeServiceBean implements FeeService{

    private final FeeRepository feeRepository;

    @Inject
    public FeeServiceBean(FeeRepository feeRepository){
        this.feeRepository = feeRepository;
    }

    @Override
    public Fee createFee(String transactionId, String acno, BigDecimal transactionAmount) {
        return null;
    }

    @Override
    public List<Fee> getAllFees() {
        return feeRepository.findAll().list();
    }

    @Override
    public List<Fee> getFeesForAcno(String acno) {
        return null;
    }
}
