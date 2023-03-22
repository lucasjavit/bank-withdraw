package com.lucasit.withdraw.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operation_type")
public class OperationType {

    @Id
    @Column(name = "operation_type_id")
    private Long id;

    @Column(name = "description")
    private String description;
}