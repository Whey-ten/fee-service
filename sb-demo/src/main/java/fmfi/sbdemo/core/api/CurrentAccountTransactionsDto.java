package fmfi.sbdemo.core.api;

import java.util.List;

public record CurrentAccountTransactionsDto(
    List<CurrentAccountTransactionListItemDto> transactions
) { }
