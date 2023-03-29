package fmfi.sbdemo.core.domain;

import fmfi.sbdemo.core.api.CurrentAccountTransactionsDto;
import fmfi.sbdemo.core.api.GetLatestCurrentAccountTransactionsUseCase;
import fmfi.sbdemo.core.domain.FindLastCurrentAccountTransactionsByAccountNumberSpi;
import org.springframework.boot.context.properties.ConfigurationProperties;

@org.springframework.stereotype.Service // register this service as Spring bean
@lombok.AllArgsConstructor
public class CurrentAccountTransactionService implements GetLatestCurrentAccountTransactionsUseCase {

    private final FindLastCurrentAccountTransactionsByAccountNumberSpi findTransactionsSpi;

    private final CurrentAccountTransactionConfigProperties configProperties;

    @Override
    public CurrentAccountTransactionsDto getLatestCurrentAccountTransactions(String accountNumber) {
        // TODO check logged user permission for account

        return findTransactionsSpi.findLastCurrentAccountTransactionsByAccountNumber(
                /* accountNumber */ accountNumber,
                /* transactionRecordCount */ configProperties.getLatestCurrentAccountTransactionsMaxRecordCount()
        );
    }

    @ConfigurationProperties("app.current-account.transaction")
    public record CurrentAccountTransactionConfigProperties(
            int getLatestCurrentAccountTransactionsMaxRecordCount
    ) { }
}
