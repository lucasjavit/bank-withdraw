package com.lucasit.withdraw.request.external.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationExternalRequestBody {

    private String name;
    private AccountExternalRequestBody account;
}
