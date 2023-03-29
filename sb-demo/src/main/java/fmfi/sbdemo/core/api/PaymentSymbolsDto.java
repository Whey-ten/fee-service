package fmfi.sbdemo.core.api;

@lombok.Builder
public record PaymentSymbolsDto(
        String variable,
        String specific,
        String constant,
        String payerReference
) {
}
