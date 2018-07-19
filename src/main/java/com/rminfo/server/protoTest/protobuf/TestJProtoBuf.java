package com.rminfo.server.protoTest.protobuf;

import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.rminfo.protobuf.item.DgtKafkaDto;
import com.rminfo.protobuf.item.MatchItemProto0;
import com.rminfo.protobuf.item.OddsItemProto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junior on 17:17 2018/7/9.
 */
public class TestJProtoBuf {

    /**
         * 编码方法，将SubscribeReqProto.SubscribeReq对象编码为字节数组
         * @param req
         * @return
         */
        private static byte[] encode(GeneratedMessageV3 req) {
            return req.toByteArray();
        }

        /**
         * 解码方法，将字节数组解码为SubscribeReqProto.SubscribeReq对象
         * @param body
         * @return
         * @throws InvalidProtocolBufferException
         */
    private static JprotobufBeanA.JprotobufBean decode(byte[] body) throws InvalidProtocolBufferException {
        return JprotobufBeanA.JprotobufBean.parseFrom(body);
    }

    private static OddsItemProto.OddsItem decodeOdds(byte[] body) throws InvalidProtocolBufferException {
        return OddsItemProto.OddsItem.parseFrom(body);
    }

    /**
     * 通过SubscribeReqProto.SubscribeReq的静态方法newBuilder创建SubscribeReqProto.SubscribeReq的
     * Builder实例，通过Builder构建器对SubscribeReq的属性进行设置，对于集合类型，通过addAllxxx()方法
     * 可以将集合对象设置到对应到的属性中，最后通过builder的build方法返回设置好属性的SubscribeReqProto.SubscribeReq对象
     * @return
     */
    private static JprotobufBeanA.JprotobufBean createSubscribeReq(){
        JprotobufBeanA.JprotobufBean.Builder builder = JprotobufBeanA.JprotobufBean.newBuilder();
        builder.setName("aaaaa");
        builder.setBoolF(true);
        builder.setId(111);
        builder.setFloatF(12.12f);
        JprotobufBeanA.ComplexTypeTest.Builder builder1 = JprotobufBeanA.ComplexTypeTest.newBuilder();
        JprotobufBeanA.SimpleTypeTest.Builder builder2 = JprotobufBeanA.SimpleTypeTest.newBuilder();
        builder2.setName("ssssss");
        builder2.setValue(3333333);
        builder1.setName("cccccccc");
        builder1.setSimpleTypeTest(builder2.build());
        builder.setSimpleTypeTest(builder1.build());
        return builder.build();
    }
/*     "periodNum":0,
             "argument":"-0.25",
             "matchPeriodId":4014531,
             "isShowOptionName_list":0,
             "isShowOptionName_detail":0,
             "oddsId":448802027,
             "primeArgument":{
        "marketName":"{"1":"Handicap","512":"亚盘"}",
                "optionList":{
            "-0.25":{
                "1":{
                    "argument":"-0.25",
                            "matchPeriodId":4014531,
                            "oddsId":448802027,
                            "oddsValue":"2.30",
                            "partnerId":0,
                            "isVisible":1,
                            "marketFullId":"992_0_0",
                            "optionName":"{"1":"H1","512":"卡拉干达矿工U18"}",
                            "marketId":992,
                            "optionNum":1
                },
                "2":{
                    "argument":"+0.25",
                            "matchPeriodId":4014531,
                            "oddsId":448802028,
                            "oddsValue":"1.54",
                            "partnerId":0,
                            "isVisible":1,
                            "marketFullId":"992_0_0",
                            "optionName":"{"1":"H2","512":"塞麦伊U18"}",
                            "marketId":992,
                            "optionNum":2
                }
            }
        },
        "correspondingMarketId":2,
                "mSort":10002,
                "marketId":992
    },
            "isPrimeArgument":"1",
            "isActive":true,
            "mSort":10002,
            "marketType":"101099",
            "marketId":2,
            "topicList":[
            "H5_inLive_1_0_1",
            "PC_inLive_0_0_1",
            "PC_inLive_1_0_1",
            "H5_inLive_0_0_1",
            "H5_homePage"
            ],
            "eventPartId":0,
            "priArgumentNeedToDelete":0,
            "leagueId":18585,
            "oddsValuesMated":{
        "1":"2.30",
                "2":"1.55"
    },
            "oddsValue":"2.30",
            "matchId":4014531,
            "homeTeamArgument":"-0.25",
            "isVisible":1,
            "optionNum":1,
            "sportId":1,
            "marketName":"{"1":"Handicap","512":"亚盘"}",
            "isInLive":1,
            "marketFullId":"2_0_0",
            "optionName":"{"1":"H1","512":"卡拉干达矿工U18"}"
},*/
    private static OddsItemProto.OddsItem createOddsItem(){
        DgtKafkaDto dto = new DgtKafkaDto();
        dto = JSON.parseObject("{\"content\":{\"baseData\":{\"periodNum\":0,\"argument\":\"-0.25\",\"matchPeriodId\":4014531,\"isShowOptionName_detail\":0,\"oddsId\":448802027,\"primeArgument\":{\"marketName\":\"{\\\"1\\\":\\\"Handicap\\\",\\\"512\\\":\\\"亚盘\\\"}\",\"optionList\":{\"-0.25\":{\"1\":{\"argument\":\"-0.25\",\"matchPeriodId\":4014531,\"oddsId\":448802027,\"oddsValue\":\"2.30\",\"partnerId\":0,\"isVisible\":1,\"marketFullId\":\"992_0_0\",\"optionName\":\"{\\\"1\\\":\\\"H1\\\",\\\"512\\\":\\\"卡拉干达矿工U18\\\"}\",\"marketId\":992,\"optionNum\":1},\"2\":{\"argument\":\"+0.25\",\"matchPeriodId\":4014531,\"oddsId\":448802028,\"oddsValue\":\"1.54\",\"partnerId\":0,\"isVisible\":1,\"marketFullId\":\"992_0_0\",\"optionName\":\"{\\\"1\\\":\\\"H2\\\",\\\"512\\\":\\\"塞麦伊U18\\\"}\",\"marketId\":992,\"optionNum\":2}}},\"correspondingMarketId\":2,\"mSort\":10002,\"marketId\":992},\"isPrimeArgument\":\"1\",\"isActive\":true,\"mSort\":10002,\"marketType\":\"101099\",\"marketId\":2,\"eventPartId\":0,\"priArgumentNeedToDelete\":0,\"leagueId\":18585,\"oddsValuesMated\":\"{\\\"1\\\":\\\"2.30\\\",\\\"2\\\":\\\"1.55\\\"}\",\"oddsValue\":\"2.30\",\"matchId\":4014531,\"homeTeamArgument\":\"-0.25\",\"isVisible\":1,\"optionNum\":1,\"sportId\":1,\"marketName\":\"{\\\"1\\\":\\\"Handicap\\\",\\\"512\\\":\\\"亚盘\\\"}\",\"isInLive\":1,\"marketFullId\":\"2_0_0\",\"optionName\":\"{\\\"1\\\":\\\"H1\\\",\\\"512\\\":\\\"卡拉干达矿工U18\\\"}\"},\"partnerData\":[{\"adjustedMarginRatio\":\"\",\"factor\":\"\",\"adjustedCoefficientRatio\":\"\"}]},\"head\":{\"dataType\":2}}",DgtKafkaDto.class);
        OddsItemProto.OddsItem.Builder builder = OddsItemProto.OddsItem.newBuilder();
        OddsItemProto.PrimeArgumentProto.Builder builder2 = OddsItemProto.PrimeArgumentProto.newBuilder();
        OddsItemProto.OptionProto.Builder builder3 = OddsItemProto.OptionProto.newBuilder();
        Map<String,Descriptors.FieldDescriptor> fields = new HashMap<>();
        Map<String,Descriptors.FieldDescriptor> fields2 = new HashMap<>();
        Map<String,Descriptors.FieldDescriptor> fields3 = new HashMap<>();
        builder.getDescriptorForType().getFields().stream().forEach(x -> fields.put(x.getName(),x));
        builder2.getDescriptorForType().getFields().stream().forEach(x -> fields2.put(x.getName(),x));
        builder3.getDescriptorForType().getFields().stream().forEach(x -> fields3.put(x.getName(),x));
        for (String key : dto.getContent().getBaseData().keySet()) {
            if(key != "primeArgument" && fields.containsKey(key)){
                builder.setField(fields.get(key),dto.getBaseDatas(key));
            }
        }
        if(dto.getContent().getBaseData().containsKey("primeArgument")){
            Map<String,Object> primeArgument = (Map<String, Object>) dto.getBaseDatas("primeArgument");
            for (String key : primeArgument.keySet()) {
                if(key != "optionList" && fields2.containsKey(key)){
                    builder2.setField(fields2.get(key),primeArgument.get(key));
                }
            }
            if(primeArgument.containsKey("optionList")){
                Map<String,Object> optionList = (Map<String, Object>) primeArgument.get("optionList");
                String argument = optionList.keySet().iterator().next();
                builder2.setPriArgument(argument);
                Map<String,Object> options = (Map<String, Object>) optionList.get(argument);
                for (String optionNum:options.keySet()) {
                    Map<String,Object> option1 = (Map<String, Object>) options.get(optionNum);
                    builder3.clear();
                    for (String key : option1.keySet()) {
                        builder3.setField(fields3.get(key),option1.get(key));
                    }
                    builder2.putOptionList(optionNum,builder3.build());
                }
            }
        }
        builder.setPrimeArgumentProto(builder2);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        Long start = System.currentTimeMillis();
        // 创建SubscribeReqProto.SubscribeReq对象
        OddsItemProto.OddsItem req = createOddsItem();
        // 输出编码前的SubscribeReqProto.SubscribeReq对象req的值
        System.out.println("Before encode : " + req.toString());
        System.out.println("Before encode size: " + req.toString().length());
        System.out.println("after encode size: " + req.toByteArray().length);
        // 获取解码后的SubscribeReqProto.SubscribeReq对象
        OddsItemProto.OddsItem req2 = decodeOdds(encode(req));
        // 输出解码后的SubscribeReqProto.SubscribeReq对象req2的值
        System.out.println("After decode : " + req2.toString());
        // 比较req与req2的值是否相等 --- true说明
        System.out.println("Assert equal : -->" + req2.equals(req));

        System.out.println("cost time: "+ (System.currentTimeMillis()-start)/1000 + "ms");

        String code = ProtobufIDLGenerator.getIDL(MatchItemProto0.class);
        System.out.println(code);
        code = ProtobufIDLGenerator.getIDL(SimpleTypeTest.class);
        //System.out.println(code);
    }
}
