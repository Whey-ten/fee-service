package sk.fmfi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Fee {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String transactionId;

    @Column
    private String acno;

    @Column
    private BigDecimal amount;

    @Column
    private LocalDateTime creationDate;
}
