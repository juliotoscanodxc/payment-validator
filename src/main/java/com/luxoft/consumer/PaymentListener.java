package com.luxoft.consumer;

import com.luxoft.dto.PaymentOrder;
import com.luxoft.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentListener {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void onReceive(ConsumerRecord<Integer, PaymentOrder> consumerRecord) {

        log.info("Received message:" + consumerRecord);

        try {
            paymentService.process(consumerRecord.value());

        } catch (InterruptedException e) {

            log.error("Error on process message: " + consumerRecord.value());

            throw new RuntimeException(e);
        }
    }
}
