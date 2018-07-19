package com.rminfo.test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by junior on 17:06 2017/12/28.
 */
public class KafkaProducerTestForCarr2 {

    public static void main(String[] args) {
        //kafka producer config
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "barcelona.footballint.com:6667");
        properties.setProperty("acks", "all");
        properties.setProperty("retries", "0");
        properties.setProperty("batch.size", "16384");
        properties.setProperty("linger.ms", "1");
        properties.setProperty("buffer.memory", "33554432");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //create kafka producer
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        String[] ids = new String[]{"3959077"};
        Random random = new Random();


        for (int i = 0; i < 8; i++) {

            //producerRecord
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("storm_webSocket_1",ids[0]+"_2_992_123456789_125099",
                    "{\"content\":{\"baseData\":{\"periodNum\":0,\"argument\":\"3.5\",\"matchPeriodId\":3959078,\"isShowOptionName_list\":1,\"isShowOptionName_detail\":1,\"oddsId\":439487792,\"isPrimeArgument\":\"0\",\"isActive\":false,\"mSort\":10005,\"marketType\":\"101099\",\"marketId\":992,\"topicList\":[\"3959077_marketType_125099\"],\"eventPartId\":0,\"priArgumentNeedToDelete\":1,\"leagueId\":15343,\"oddsValuesMated\":{},\"oddsValue\":\"0.00\",\"matchId\":3959077,\"homeTeamArgument\":\"9.50\",\"isVisible\":0,\"optionNum\":1,\"sportId\":1,\"marketName\":\"{\\\"1\\\":\\\"Total\\\",\\\"512\\\":\\\"附加大小盘\\\"}\",\"isInLive\":1,\"marketFullId\":\"992_1_1\",\"optionName\":\"{\\\"1\\\":\\\"O\\\",\\\"512\\\":\\\"高于\\\"}\"},\"partnerData\":[{\"adjustedMarginRatio\":\"\",\"factor\":\"\",\"adjustedCoefficientRatio\":\"\"}]},\"head\":{\"dataType\":2}}");
            //send message
            Future<RecordMetadata> future = producer.send(producerRecord);
            try {
                RecordMetadata recordMetadata = future.get();
                if(future.isDone()){
                    System.out.println("kafka数据：prodcer topic:" + recordMetadata.topic() + ",prodcer partition:" + recordMetadata.partition() + ",prodcer offset:" + recordMetadata.offset());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        while (true){

        }
    }
}
