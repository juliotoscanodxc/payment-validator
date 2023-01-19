package com.luxoft.service;

import com.luxoft.dto.PaymentOrder;

public interface PaymentService {
    void process(PaymentOrder paymentOrder) throws InterruptedException;
}
