package fmfi.sbdemo.core.domain;

import fmfi.sbdemo.core.api.CurrentAccountTransactionsDto;

public interface FindLastCurrentAccountTransactionsByAccountNumberSpi {

  CurrentAccountTransactionsDto findLastCurrentAccountTransactionsByAccountNumber(
      String accountNumber,
      int transactionRecordCount
  );
}
