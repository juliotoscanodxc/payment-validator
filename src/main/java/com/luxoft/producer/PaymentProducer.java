package com.luxoft.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.dto.PaymentReceipt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Random;

@Configuration
@Slf4j
public class PaymentProducer {

    @Value(value = "${spring.kafka.producer.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<Integer, PaymentReceipt> paymentOrderKafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(PaymentReceipt paymentReceipt) throws JsonProcessingException {

        Integer key = new Random().nextInt(1000000);

        ListenableFuture<SendResult<Integer, PaymentReceipt>> future = paymentOrderKafkaTemplate.send(topic, key, paymentReceipt);

        future.addCallback(new ListenableFutureCallback<SendResult<Integer, PaymentReceipt>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send =" + paymentReceipt + " due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<Integer, PaymentReceipt> result) {
                log.info("Sent message=" + paymentReceipt + " with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }
        });
    }
}
