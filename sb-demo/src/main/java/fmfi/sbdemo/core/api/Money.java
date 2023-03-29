package fmfi.sbdemo.core.api;

import java.math.BigDecimal;

public record Money(BigDecimal amount, String currency) {

  public static Money eur(long amount) {
    return new Money(BigDecimal.valueOf(amount), "EUR");
  }

}
