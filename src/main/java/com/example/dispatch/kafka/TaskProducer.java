package com.example.dispatch.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @ClassName:
 * @Auther L.Chen
 * @CreateDate
 * @Description TODO
 */
@Component
public class TaskProducer {
    @Value("${task.topic}")
    private String topic;
    @Autowired
    private KafkaProducer<String, String> producer;

    public void pushTask(String stage, String meta) {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, stage + "|" + meta);
        producer.send(record);
    }
}
