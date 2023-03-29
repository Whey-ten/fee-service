package fmfi.sbdemo.core.api;

public interface CreatePaymentOrderUseCase {
  PaymentOrderDto createPaymentOrder(PaymentOrderRequestDto paymentOrderRequest);
}
