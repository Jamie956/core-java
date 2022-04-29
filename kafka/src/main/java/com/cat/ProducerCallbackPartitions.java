package com.cat;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 指定分区 或 指定key
 */
public class ProducerCallbackPartitions {
    public static void main(String[] args) {

        Properties p = new Properties();
        //连接kafka 集群
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "cp1:9092,cp2:9092,cp3:9092");

        //key value 序列化方式
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //自定义分区
        p.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitions.class.getName());

        //生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(p);

        for (int i = 0; i < 5; i++) {
            //发送到指定分区
//            producer.send(new ProducerRecord<>("first-topic", 0, "", "value" + i), new Callback() {
            //发送指定key，哈希算法
//            producer.send(new ProducerRecord<>("first-topic", "a", "value" + i), new Callback() {
            //使用自定义分区
            producer.send(new ProducerRecord<>("first-topic", "dog" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.printf("topic: %s, partitions: %s", recordMetadata.topic(), recordMetadata.partition());
                    System.out.println();
                }
            });
        }

        producer.close();
    }
}
