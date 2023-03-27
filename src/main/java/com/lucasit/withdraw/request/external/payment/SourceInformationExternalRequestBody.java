package com.lucasit.withdraw.request.external.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceInformationExternalRequestBody {

    private String name;

    public static SourceInformationExternalRequestBody getBuild(String information) {
        return SourceInformationExternalRequestBody.builder()
                .name(information)
                .build();
    }
}
