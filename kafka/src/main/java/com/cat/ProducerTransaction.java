package com.cat;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 开启事务
 */
public class ProducerTransaction {
    public static void main(String[] args) {

        Properties p = new Properties();
        //连接kafka 集群
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "cp1:9092,cp2:9092,cp3:9092");

        //key value 序列化方式
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //事务必须配置 transaction id
        p.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction_id_01");

        //生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(p);

        producer.initTransactions();
        producer.beginTransaction();

        try {
            for (int i = 0; i < 5; i++) {
                producer.send(new ProducerRecord<>("first-topic", "value" + i));
            }
            //模拟异常
//            int i = 1 / 0;
            producer.commitTransaction();
        } catch (Exception e) {
            producer.abortTransaction();
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
