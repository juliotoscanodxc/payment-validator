package com.luxoft.dto;

import com.luxoft.enuns.PaymentStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentReceipt {

    private String username;

    private double debitAmount;

    private PaymentStatus status;
}
