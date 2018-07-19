package com.rminfo.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

/**
 * Created by junior on 13:22 2018/3/23.
 */
public class KafkaConsumerTest2 {

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
        ConcurrentMessageListenerContainer<String, String> container = KafkaConsumerTest.createConcurrentContainer(containerProps);
        container.setBeanName("testAuto2");
        container.setConcurrency(2);
        container.start();
        /*KafkaMessageListenerContainer<String, String> container2 = KafkaConsumerTest.createContainer(containerProps);
        container2.setBeanName("testAuto3");
        container2.start();*/
        while (true){
        }
    }

}
