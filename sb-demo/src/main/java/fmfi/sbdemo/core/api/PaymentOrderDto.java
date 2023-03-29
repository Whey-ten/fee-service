package fmfi.sbdemo.core.api;

import java.time.LocalDate;

@lombok.Builder
public record PaymentOrderDto(
        String transactionId,
        TransactionStatus status,
        LocalDate processingDate,
        String senderAccountIban,
        String targetAccountIban,
        PaymentSymbolsDto paymentSymbols,
        PaymentDetailDto paymentDetail
) {
}
