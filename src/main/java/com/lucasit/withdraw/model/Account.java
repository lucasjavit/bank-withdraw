package com.lucasit.withdraw.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "national_id_Number")
    private Long nationalIdNumber;

    @Column(name = "account_number", unique = true)
    private Long accountNumber;

    @Column(name = "bank_name")
    private String bankName;

    private BigDecimal balance;

    private AccountType accountType;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
