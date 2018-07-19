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
public class KafkaProducerTestForCarr {

    public static void main(String[] args) {
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
        String[] ids = new String[]{"3593677"};
        Random random = new Random();


        while (true){
            for (int i = 0; i < 8; i++) {
                Long l3 = 3L;
                Long l46 = 46L;
                Long leagueId = 10834L;
                int marketType = 101099;
                if(i%2==0){
                    l3 = 46L;
                    l46 = 3L;
                }
                //producerRecord
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("storm_webSocket_1",ids[0]+"_2_992_123456789_"+marketType,"{\"head\":{\"dataType\":2},\"content\":{\"baseData\":{\"periodNum\":0,\"argument\":\"8.5\",\"matchPeriodId\":3591801,\"oddsId\":367485331,\"primeArgument\":{\"optionList\":{\"7.5\":{\"1\":{\"oddsId\":367485331,\"matchPeriodId\":3591801,\"marketId\":3,\"argument\":7.50,\"optionNum\":1,\"oddsValue\":"+random.nextInt(100)/10.0+",\"isWinner\":null,\"updatedTime\":\"2017-12-28T14:16:40.000+0800\"},\"2\":{\"oddsId\":367485330,\"matchPeriodId\":3591801,\"marketId\":3,\"argument\":7.50,\"optionNum\":2,\"oddsValue\":"+random.nextInt(100)/10.0+",\"isWinner\":null,\"updatedTime\":\"2017-12-28T14:13:43.000+0800\"}}},\"correspondingMarketId\":"+l3+",\"marketId\":"+l46+"},\"isPrimeArgument\":\"0\",\"isVisible\":true,\"isActive\":true,\"marketId\":46,\"optionNum\":1,\"eventPartId\":0,\"sportId\":3,\"serial\":4,\"leagueId\":"+leagueId+",\"oddsValue\":\"2.55\",\"matchId\":"+ids[0]+",\"marketType\":"+marketType+"},\"partnerData\":[]}}");
                //send message
                Future<RecordMetadata> future= producer.send (producerRecord);
                try {
                    RecordMetadata  recordMetadata  =future.get ();
                    System.out.println ("kafka数据：prodcer topic:"+recordMetadata.topic ()+",prodcer partition:"+recordMetadata.partition ()+",prodcer offset:"+recordMetadata.offset ());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace ();
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
