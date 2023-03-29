package fmfi.sbdemo.core.domain;

import fmfi.sbdemo.core.api.PaymentOrderDto;
import fmfi.sbdemo.core.api.PaymentOrderRequestDto;
import fmfi.sbdemo.core.api.TransactionStatus;

@org.springframework.stereotype.Component
public class PaymentOrderBackendSystemMockAdapter implements SendPaymentOrderRequestSpi {
    @Override
    public PaymentOrderDto sendPaymentOrderRequest(PaymentOrderRequestDto paymentOrderRequest) {
        return PaymentOrderDto.builder()
                .transactionId(java.util.UUID.randomUUID().toString())
                .processingDate(java.time.LocalDate.now())
                .status(TransactionStatus.PROCESSED)
                .senderAccountIban(paymentOrderRequest.senderAccountIban())
                .targetAccountIban(paymentOrderRequest.targetAccountIban())
                .paymentDetail(paymentOrderRequest.paymentDetail())
                .paymentSymbols(paymentOrderRequest.paymentSymbols())
                .build();
    }
}
