package fmfi.sbdemo.core.api;

import java.time.LocalDate;

@lombok.Builder
public record PaymentDetailDto(
        Money amount,
        LocalDate effectiveDate,
        String description
) {}


