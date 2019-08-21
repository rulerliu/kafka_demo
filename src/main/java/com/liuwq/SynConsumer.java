package com.liuwq;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class SynConsumer {
    private static Properties getProps() {
        Properties properties = new Properties();
        //连接broker集群地址，多个broker逗号隔开
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        //配置消费者所属的分组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
        //是否自动ack
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        //若果是自动ack，那么自动ack的频率是多长
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //反序列化key
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerDeserializer");
        //反序列化value
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        //消费者加入group时，消费offset设置策略，earliest重置offset为最早的偏移地址，latest重置ofsset为已经消费的最大偏移，none没有offset时抛异常
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //每次允许拉取最大的消息数量
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5);
        return properties;
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        //设置kafka集群的地址
        props.put("bootstrap.servers", "127.0.0.1:9092");
        //设置消费者组，组名字自定义，组名字相同的消费者在一个组
        props.put("group.id", "my_group");
        //开启offset自动提交
        props.put("enable.auto.commit", "true");
        //自动提交时间间隔
        props.put("auto.commit.interval.ms", "1000");
        //序列化器
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //实例化一个消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //消费者订阅主题，可以订阅多个主题
        consumer.subscribe(Arrays.asList("test", "test2"), new ConsumerRebalanceListener() {

            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
               System.out.println("threadId = {}, onPartitionsRevoked." + Thread.currentThread().getId());
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                System.out.println("threadId = {}, onPartitionsAssigned." + Thread.currentThread().getId());

            }
        });

        //死循环不停的从broker中拿数据
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
}