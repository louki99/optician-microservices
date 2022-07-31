package com.technostack.aio.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProfileModalityPayment {

    private Long profileId;
    private Long modalityPaymentId;

    private Boolean hold;
}
