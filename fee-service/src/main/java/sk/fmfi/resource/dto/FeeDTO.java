package sk.fmfi.resource.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FeeDTO {
    private String transactionId;

    private String acno;

    private BigDecimal amount;
}
