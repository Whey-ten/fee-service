package sk.fmfi.service;

import sk.fmfi.model.Fee;

import java.math.BigDecimal;
import java.util.List;

public interface FeeService {
    Fee createFee(String transactionId, String acno, BigDecimal transactionAmount);

    List<Fee> getAllFees();

    List<Fee> getFeesForAcno(String acno);
}
