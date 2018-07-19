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
public class KafkaProducerTest  {

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
        String[] ids = new String[]{"3596074","3593923","3593644","3593498","3593266","3593265","3593253","3593249"};
        //String[] ids = new String[]{"35960741","35939231","35936441","35934981","35932661","35932651","35932531","35932491"};
        Random random = new Random();
        while (true){
            for (int i = 0; i < 8; i++) {
                //producerRecord
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("storm_webSocket_1",ids[i%8]+"_2_"+i+"_"+random.nextInt(100000)+"_101001","{\"head\":{\"dataType\":2},\"content\":{\"baseData\":{\"periodNum\":0,\"argument\":\"8.5\",\"matchPeriodId\":3595344,\"oddsId\":"+random.nextInt(10000000)+",\"primeArgument\":{\"optionList\":{\"7.5\":{\"1\":{\"oddsId\":367691601,\"matchPeriodId\":3591801,\"marketId\":46,\"argument\":7.50,\"optionNum\":1,\"oddsValue\":1.62,\"isWinner\":null,\"updatedTime\":\"2017-12-28T14:16:40.000+0800\"},\"2\":{\"oddsId\":367691602,\"matchPeriodId\":3591801,\"marketId\":46,\"argument\":7.50,\"optionNum\":2,\"oddsValue\":1.63,\"isWinner\":null,\"updatedTime\":\"2017-12-28T14:13:43.000+0800\"}}},\"correspondingMarketId\":3,\"marketId\":46},\"isPrimeArgument\":\"0\",\"isVisible\":true,\"isActive\":true,\"marketId\":992,\"optionNum\":"+i+",\"eventPartId\":0,\"sportId\":10,\"serial\":4,\"leagueId\":2004,\"oddsValue\":\"2.55\",\"matchId\":"+ids[i%8]+",\"marketType\":101001},\"partnerData\":[{\"adjustedCoefficientRatio\":\"1.01\"}]}}");
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
