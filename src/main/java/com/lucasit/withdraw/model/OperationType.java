package com.lucasit.withdraw.model;


import com.lucasit.withdraw.operations.OperationTypeStrategy;
import com.lucasit.withdraw.operations.PaymentOperationTypeStrategy;
import com.lucasit.withdraw.operations.TopUpOperationTypeStrategy;
import com.lucasit.withdraw.operations.WithDrawOperationTypeStrategy;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operation_type")
public class OperationType {

    @Id
    @GeneratedValue
    @Column(name = "operation_type_id")
    private Long id;

    @Column(name = "description")
    private String description;

    public static OperationTypeStrategy OperationType(Long operationId) {

        switch (operationId.intValue()) {
            case 1:
                return new TopUpOperationTypeStrategy();
            case 2:
                return new WithDrawOperationTypeStrategy();
            case 3:
                return new PaymentOperationTypeStrategy();

        }
        return null;
    }

}