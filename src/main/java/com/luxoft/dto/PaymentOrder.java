package com.luxoft.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentOrder {

    private String username;

    private double debitAmount;
}
