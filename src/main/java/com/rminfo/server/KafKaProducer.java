package com.rminfo.server;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016/5/16.
 */
public class KafKaProducer {

public  static boolean inBoundKafka(String topic,String key,String value){
    //kafka producer config
    Properties properties = new Properties ();
    properties.setProperty ("bootstrap.servers","barcelona.leadadvancetech.com:6667,madrid.leadadvancetech.com:6667") ;
    properties.setProperty ("acks","all");
    properties.setProperty ("retries","0");
    properties.setProperty ("batch.size","16384");
    properties.setProperty ("linger.ms","1");
    properties.setProperty ("buffer.memory","33554432");
    properties.setProperty ("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    properties.setProperty ("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    //create kafka producer
    Producer<String,String> producer = new KafkaProducer<String,String>(properties);
    //producerRecord
    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic,key,value);
    //send message
    Future<RecordMetadata> future= producer.send (producerRecord);

    boolean result= true;
    try {
        RecordMetadata  recordMetadata  =future.get ();
        System.out.println ("kafka数据：prodcer topic:"+recordMetadata.topic ()+",prodcer partition:"+recordMetadata.partition ()+",prodcer offset:"+recordMetadata.offset ());
    } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace ();
        result = false;
    } finally {
        producer.close ();
    }
    return result;
}

}