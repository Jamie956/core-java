package com.cat;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 同步等待
 */
public class ProducerSync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties p = new Properties();
        //连接kafka 集群
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "cp1:9092,cp2:9092,cp3:9092");

        //key value 序列化方式
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(p);

        for (int i = 0; i < 5; i++) {
            //同步发送
            producer.send(new ProducerRecord<>("first-topic", "value"+i)).get();
        }

        producer.close();
    }
}
