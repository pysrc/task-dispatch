package com.example.dispatch;

import com.example.dispatch.stage.Dispatcher;
import com.example.dispatch.tasks.StartTask;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;


@SpringBootApplication
public class TaskDispatchApplication implements CommandLineRunner {
    @Autowired
    private KafkaConsumer<String, String> consumer;
    @Autowired
    private Dispatcher dispatcher;
    @Autowired
    private StartTask startTask;

    public static void main(String[] args) {
        SpringApplication.run(TaskDispatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 添加任务
        for (int i = 0; i < 30; i++) {
            String xml = "<O>UPD</O><C>TABLE_NAME</C><D>table" + i + "</D><C>MYH_ID</C><D>1700242587736</D><C>SEND_ORG_ID</C><D></D><C>RECV_ORG_IDS</C><D></D><C>KEYS</C><D>17001379402</D><C>VOCATION_ID</C><D>19</D><C>UPDATE_OPERATOR_ID</C><D>469155</D><C>CUSTOMER_ID</C><D>17000750069</D><C>REGISTERED_FUND</C><D>860</D><C>INVOICE_NAME</C><D>攀枝花市农林科学研究院</D><C>INVOICE_TEL</C><D>0812-2909832</D><C>INVOICE_ADDR</C><D>攀枝花大道南段（沙沟附近）</D>";
            startTask.run(xml);
        }
        while (true) {
            Duration timeout = Duration.ofMillis(100);
            ConsumerRecords<String, String> records = consumer.poll(timeout);
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                dispatcher.runTask(value);
            }
        }
    }
}
