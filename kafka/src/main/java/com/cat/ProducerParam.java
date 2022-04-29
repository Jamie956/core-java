package com.cat;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 缓冲区配置
 */
public class ProducerParam {
    public static void main(String[] args) {

        Properties p = new Properties();
        //连接kafka 集群
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "cp1:9092,cp2:9092,cp3:9092");

        //key value 序列化方式
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //缓冲区大小 32m
        p.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 32*1024*1024);
        //批次大小 16k
        p.put(ProducerConfig.BATCH_SIZE_CONFIG, 16*1024);
        //linger.ms
        p.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        //压缩方式
        p.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");


        //生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(p);

        for (int i = 0; i < 5; i++) {
            //异步发送
            producer.send(new ProducerRecord<>("first-topic", "value"+i));
        }

        producer.close();
    }
}
