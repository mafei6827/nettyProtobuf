package com.rminfo.test;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junior on 13:22 2018/3/23.
 */
public class KafkaConsumerTest {

    public static void main(String[] args) throws InterruptedException {
        ContainerProperties containerProps = new ContainerProperties("topic1");
        containerProps.setMessageListener(new MessageListener<String, String>() {

            @Override
            public void onMessage(ConsumerRecord<String, String> message) {
                try {
                    System.out.println(message.value());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        KafkaMessageListenerContainer<String, String> container = createContainer(containerProps);
        container.setBeanName("testAuto");
        container.start();
        //producer
        KafkaTemplate<String, String> template = createTemplate();
        template.setDefaultTopic("topic1");
        int count = 0;
        while (true){
            count++;
            template.sendDefault(count+"", count+"foo");
            template.flush();
            Thread.sleep(2000);
        }
    }


    public static KafkaMessageListenerContainer<String, String> createContainer(
            ContainerProperties containerProps) {
        Map<String, Object> props = consumerProps();
        DefaultKafkaConsumerFactory<String, String> cf =
                new DefaultKafkaConsumerFactory<String, String>(props);
        KafkaMessageListenerContainer<String, String> container =
                new KafkaMessageListenerContainer<>(cf, containerProps);
        return container;
    }

    public static ConcurrentMessageListenerContainer<String, String> createConcurrentContainer(ContainerProperties containerProps) {
        Map<String, Object> props = consumerProps();
        DefaultKafkaConsumerFactory<String, String> cf =
                new DefaultKafkaConsumerFactory<String, String>(props);
        ConcurrentMessageListenerContainer<String, String> container =
                new ConcurrentMessageListenerContainer<>(cf, containerProps);
        return container;
    }

    public static KafkaTemplate<String, String> createTemplate() {
        Map<String, Object> senderProps = senderProps();
        ProducerFactory<String, String> pf =
                new DefaultKafkaProducerFactory<String, String>(senderProps);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(pf);
        return template;
    }

    public static Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.17:2181");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "56t");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,  StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    public static Map<String, Object> senderProps() {
        Map<String, Object> properties = new HashMap<>();
        properties.put ("bootstrap.servers","barcelona.leadadvancetech.com:6667,madrid.leadadvancetech.com:6667") ;
        properties.put ("acks","all");
        properties.put ("retries","0");
        properties.put ("batch.size","16384");
        properties.put ("linger.ms","1");
        properties.put ("buffer.memory","33554432");
        properties.put ("key.serializer",StringSerializer.class);
        properties.put ("value.serializer",StringSerializer.class);
        return properties;
    }
}
