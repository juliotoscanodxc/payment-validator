package com.luxoft.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luxoft.dto.PaymentOrder;
import com.luxoft.dto.PaymentReceipt;
import com.luxoft.enuns.PaymentStatus;
import com.luxoft.producer.PaymentProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentProducer paymentProducer;

    @Override
    public void process(PaymentOrder paymentOrder) throws InterruptedException {
        log.info("Processing payment...");
        Thread.currentThread().wait(5000);
        log.info("Payment requested to bank...");
        Thread.currentThread().wait(5000);
        log.info("Payment accepted.");
        log.info("issuing receipt...");
        try {
            paymentProducer.send(generateReceipt(paymentOrder));
        } catch (JsonProcessingException e) {
            log.error("Error on try to generate receipt: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private PaymentReceipt generateReceipt(PaymentOrder paymentOrder) {
        PaymentReceipt receipt = new PaymentReceipt();
        receipt.setUsername(paymentOrder.getUsername());
        receipt.setDebitAmount(paymentOrder.getDebitAmount());
        receipt.setStatus(PaymentStatus.SUCCESS);
        return receipt;
    }
}
