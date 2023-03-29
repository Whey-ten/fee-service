package fmfi.sbdemo.core.domain;

import fmfi.sbdemo.core.api.PaymentOrderDto;

public interface ApplyFeesForPaymentOrderSpi {
    void applyFeesForPaymentOrder(PaymentOrderDto paymentOrder);
}
