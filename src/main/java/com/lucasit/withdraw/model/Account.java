package com.lucasit.withdraw.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_number", unique = true)
    private Long accountNumber;

    @Column(name = "national_id_Number")
    private Long nationalIdNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
