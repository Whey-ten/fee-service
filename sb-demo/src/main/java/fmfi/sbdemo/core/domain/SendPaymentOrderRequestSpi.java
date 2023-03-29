package fmfi.sbdemo.core.domain;

import fmfi.sbdemo.core.api.PaymentOrderDto;
import fmfi.sbdemo.core.api.PaymentOrderRequestDto;

public interface SendPaymentOrderRequestSpi {
    PaymentOrderDto sendPaymentOrderRequest(PaymentOrderRequestDto paymentOrderRequest);
}
