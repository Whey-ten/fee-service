package fmfi.sbdemo.adapter.integration;

import fmfi.sbdemo.core.api.PaymentOrderDto;
import fmfi.sbdemo.core.domain.ApplyFeesForPaymentOrderSpi;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Component
@lombok.extern.slf4j.Slf4j
@lombok.AllArgsConstructor
public class FeesBackendRestClientAdapter implements ApplyFeesForPaymentOrderSpi {

    private final RestTemplate feesRestTemplate;

    @Override
    public void applyFeesForPaymentOrder(PaymentOrderDto paymentOrder) {
        var feeRequest = FeeDto.builder()
                .transactionId(paymentOrder.transactionId())
                .acno(paymentOrder.senderAccountIban())
                .transactionAmount(paymentOrder.paymentDetail().amount().amount())
                .build();

        var response = feesRestTemplate.postForEntity("/fee", feeRequest, Void.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            log.warn("Fees not applied for payment order {}", paymentOrder);
        }
    }
}

@lombok.Builder
record FeeDto(
        String transactionId,
        String acno,
        java.math.BigDecimal transactionAmount
) {}
