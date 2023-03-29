package fmfi.sbdemo.adapter.persistance;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import fmfi.sbdemo.core.api.TransactionStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@DataJpaTest
class TransactionPersistenceAdapterTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        TransactionPersistenceAdapter testPersistenceAdapter(TransactionRepository repository) {
            return new TransactionPersistenceAdapter(repository);
        }
    }

    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    @Qualifier("testPersistenceAdapter")
    private TransactionPersistenceAdapter transactionAdapter;

    @Test
    void findBySenderAccountIbanOrTargetAccountIbanOrderByPaymentDetailEffectiveDateDesc() {
        // setup
        String accountNumber = "SK8975000000000012345671";

        var debitTransaction = TransactionEntity.builder()
                .transactionId(UUID.randomUUID().toString())
                .status(TransactionStatus.PROCESSED)
                .senderAccount(AccountEmbeddable.builder()
                        .iban(accountNumber)
                        .balance(new MoneyEmbeddable(BigDecimal.valueOf(958), "EUR"))
                        .build())
                .targetAccount(AccountEmbeddable.builder()
                        .iban("DE89370400440532013000")
                        .name("Acme corp.")
                        .bic("DEUTDEFFXXX")
                        .build())
                .paymentDetail(PaymentDetailEmbeddable.builder()
                        .amount(new MoneyEmbeddable(BigDecimal.valueOf(42), "EUR"))
                        .effectiveDate(LocalDate.of(2022, 9, 24))
                        .description("Anvil")
                        .build())
                .paymentSymbols(PaymentSymbolsEmbeddable.builder()
                        .variable("123456")
                        .build())
                .build();

        var creditTransaction = TransactionEntity.builder()
                .transactionId(UUID.randomUUID().toString())
                .status(TransactionStatus.PROCESSED)
                .senderAccount(AccountEmbeddable.builder()
                        .iban("DE89370400440532013000")
                        .name("Acme corp.")
                        .bic("DEUTDEFFXXX")
                        .build())
                .targetAccount(AccountEmbeddable.builder()
                        .iban(accountNumber)
                        .balance(new MoneyEmbeddable(BigDecimal.valueOf(970), "EUR"))
                        .build())
                .paymentDetail(PaymentDetailEmbeddable.builder()
                        .amount(new MoneyEmbeddable(BigDecimal.valueOf(12), "EUR"))
                        .effectiveDate(LocalDate.of(2022, 9, 25))
                        .description("Cashback")
                        .build())
                .build();

        var otherAccountTransaction = TransactionEntity.builder()
                .transactionId(UUID.randomUUID().toString())
                .status(TransactionStatus.PROCESSED)
                .senderAccount(AccountEmbeddable.builder()
                        .iban("DE89370400440532013000")
                        .name("Acme corp.")
                        .bic("DEUTDEFFXXX")
                        .build())
                .targetAccount(AccountEmbeddable.builder()
                        .iban("SK3112000000198742637541")
                        .balance(new MoneyEmbeddable(BigDecimal.valueOf(1010), "EUR"))
                        .build())
                .paymentDetail(PaymentDetailEmbeddable.builder()
                        .amount(new MoneyEmbeddable(BigDecimal.valueOf(10), "EUR"))
                        .effectiveDate(LocalDate.of(2022, 9, 26))
                        .description("Cashback")
                        .build())
                .build();

        transactionRepository.saveAll(
                List.of(debitTransaction, creditTransaction, otherAccountTransaction)
        );

        // run
        var result = transactionAdapter
                .findLastCurrentAccountTransactionsByAccountNumber(accountNumber, 10);

        // verify
        assertThat(result.transactions().stream().map(it -> it.transactionId()).toList())
                .isEqualTo(List.of(creditTransaction.getTransactionId(), debitTransaction.getTransactionId()));
    }
}
