package fmfi.sbdemo.adapter.persistance;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository // register this repository as Spring bean
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

  /**
   * Selects all the transactions with either sourceAccount.iban equal to sourceAccountIban
   * or targetAccount.iban equal to targetAccountIban.
   * Orders the record by paymentDetail.effectiveDate in descending order.
   *
   * @param sourceAccountIban sourceAccount.iban
   * @param targetAccountIban targetAccount.iban
   * @param pageable pagination data used to limit count of selected records
   * @return list of transaction records matching the selection criteria
   */
  java.util.List<TransactionEntity> findBySenderAccountIbanOrTargetAccountIbanOrderByPaymentDetailEffectiveDateDesc(
      String sourceAccountIban,
      String targetAccountIban,
      Pageable pageable
  );
}
