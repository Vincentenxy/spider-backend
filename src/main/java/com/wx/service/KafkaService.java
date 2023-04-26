package com.wx.service;

import com.alibaba.fastjson2.JSONObject;
import com.wx.service.es.WriteEs;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class KafkaService {

    @Autowired
    private WriteEs writeEsService;

    @KafkaListener(topics = "test", groupId = "console-consumer-90771")
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            String message = (String) kafkaMessage.get();
            log.info("message: {}", message);
            JSONObject pj = JSONObject.parse(message);
            writeEsService.index(pj, "articles", "");
        }
    }




}
