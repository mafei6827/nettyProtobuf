package com.rminfo.server.protoTest.protobuf;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.rminfo.protobuf.item.DgtKafkaDto;
import com.rminfo.protobuf.item.MatchItemProto;
import com.rminfo.protobuf.item.OddsItemProto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junior on 17:17 2018/7/9.
 */
public class TestJProtoBuf {

    /**
     * 编码方法，将SubscribeReqProto.SubscribeReq对象编码为字节数组
     *
     * @param req
     * @return
     */
    private static byte[] encode(GeneratedMessageV3 req) {
        return req.toByteArray();
    }

    /**
     * 解码方法，将字节数组解码为SubscribeReqProto.SubscribeReq对象
     *
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

    private static MatchItemProto.MatchItem decodeMatch(byte[] body) throws InvalidProtocolBufferException {
        return MatchItemProto.MatchItem.parseFrom(body);
    }

    /**
     * 通过SubscribeReqProto.SubscribeReq的静态方法newBuilder创建SubscribeReqProto.SubscribeReq的
     * Builder实例，通过Builder构建器对SubscribeReq的属性进行设置，对于集合类型，通过addAllxxx()方法
     * 可以将集合对象设置到对应到的属性中，最后通过builder的build方法返回设置好属性的SubscribeReqProto.SubscribeReq对象
     *
     * @return
     */
    private static JprotobufBeanA.JprotobufBean createSubscribeReq() {
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
    private static OddsItemProto.OddsItem createOddsItem() {
        DgtKafkaDto dto = new DgtKafkaDto();
        dto = JSON.parseObject("{\"content\":{\"baseData\":{\"periodNum\":0,\"argument\":\"-0.25\",\"matchPeriodId\":4014531,\"isShowOptionName_detail\":0,\"oddsId\":448802027,\"primeArgument\":{\"marketName\":\"{\\\"1\\\":\\\"Handicap\\\",\\\"512\\\":\\\"亚盘\\\"}\",\"optionList\":{\"-0.25\":{\"1\":{\"argument\":\"-0.25\",\"matchPeriodId\":4014531,\"oddsId\":448802027,\"oddsValue\":\"2.30\",\"partnerId\":0,\"isVisible\":1,\"marketFullId\":\"992_0_0\",\"optionName\":\"{\\\"1\\\":\\\"H1\\\",\\\"512\\\":\\\"卡拉干达矿工U18\\\"}\",\"marketId\":992,\"optionNum\":1},\"2\":{\"argument\":\"+0.25\",\"matchPeriodId\":4014531,\"oddsId\":448802028,\"oddsValue\":\"1.54\",\"partnerId\":0,\"isVisible\":1,\"marketFullId\":\"992_0_0\",\"optionName\":\"{\\\"1\\\":\\\"H2\\\",\\\"512\\\":\\\"塞麦伊U18\\\"}\",\"marketId\":992,\"optionNum\":2}}},\"correspondingMarketId\":2,\"mSort\":10002,\"marketId\":992},\"isPrimeArgument\":\"1\",\"isActive\":true,\"mSort\":10002,\"marketType\":\"101099\",\"marketId\":2,\"eventPartId\":0,\"priArgumentNeedToDelete\":0,\"leagueId\":18585,\"oddsValuesMated\":\"{\\\"1\\\":\\\"2.30\\\",\\\"2\\\":\\\"1.55\\\"}\",\"oddsValue\":\"2.30\",\"matchId\":4014531,\"homeTeamArgument\":\"-0.25\",\"isVisible\":1,\"optionNum\":1,\"sportId\":1,\"marketName\":\"{\\\"1\\\":\\\"Handicap\\\",\\\"512\\\":\\\"亚盘\\\"}\",\"isInLive\":1,\"marketFullId\":\"2_0_0\",\"optionName\":\"{\\\"1\\\":\\\"H1\\\",\\\"512\\\":\\\"卡拉干达矿工U18\\\"}\"},\"partnerData\":[{\"adjustedMarginRatio\":\"\",\"factor\":\"\",\"adjustedCoefficientRatio\":\"\"}]},\"head\":{\"dataType\":2}}", DgtKafkaDto.class);
        OddsItemProto.OddsItem.Builder builder = OddsItemProto.OddsItem.newBuilder();
        OddsItemProto.PrimeArgumentProto.Builder builder2 = OddsItemProto.PrimeArgumentProto.newBuilder();
        OddsItemProto.OptionProto.Builder builder3 = OddsItemProto.OptionProto.newBuilder();
        Map<String, Descriptors.FieldDescriptor> fields = new HashMap<>();
        Map<String, Descriptors.FieldDescriptor> fields2 = new HashMap<>();
        Map<String, Descriptors.FieldDescriptor> fields3 = new HashMap<>();
        builder.getDescriptorForType().getFields().stream().forEach(x -> fields.put(x.getName(), x));
        builder2.getDescriptorForType().getFields().stream().forEach(x -> fields2.put(x.getName(), x));
        builder3.getDescriptorForType().getFields().stream().forEach(x -> fields3.put(x.getName(), x));
        for (String key : dto.getContent().getBaseData().keySet()) {
            if (key != "primeArgument" && fields.containsKey(key)) {
                builder.setField(fields.get(key), dto.getBaseDatas(key));
            }
        }
        if (dto.getContent().getBaseData().containsKey("primeArgument")) {
            Map<String, Object> primeArgument = (Map<String, Object>) dto.getBaseDatas("primeArgument");
            for (String key : primeArgument.keySet()) {
                if (key != "optionList" && fields2.containsKey(key)) {
                    builder2.setField(fields2.get(key), primeArgument.get(key));
                }
            }
            if (primeArgument.containsKey("optionList")) {
                Map<String, Object> optionList = (Map<String, Object>) primeArgument.get("optionList");
                String argument = optionList.keySet().iterator().next();
                builder2.setPriArgument(argument);
                Map<String, Object> options = (Map<String, Object>) optionList.get(argument);
                for (String optionNum : options.keySet()) {
                    Map<String, Object> option1 = (Map<String, Object>) options.get(optionNum);
                    builder3.clear();
                    for (String key : option1.keySet()) {
                        builder3.setField(fields3.get(key), option1.get(key));
                    }
                    builder2.putOptionList(optionNum, builder3.build());
                }
            }
        }
        builder.setPrimeArgumentProto(builder2);
        return builder.build();
    }

    private static MatchItemProto.MatchItem createMatchItem() {
        DgtKafkaDto dto = new DgtKafkaDto();
        dto = JSON.parseObject("{\"content\":{\"baseData\":{\"matchPeriodId\":4014540,\"score2\":1,\"currentScore\":\"0:0\",\"matchStatusName\":\"{\\\"1\\\":\\\"3rd set\\\",\\\"512\\\":\\\"第3盘\\\"}\",\"regionName\":\"ITF\",\"isActive\":1,\"periodSetScore\":\"4:6 - 6:0 - 2:1\",\"leagueName\":\"{\\\"1\\\":\\\"ITF. Men. Taipei. Hard. Chinese Taipei\\\",\\\"512\\\":\\\"ITF中国台北硬地赛男单\\\"}\",\"matchStatus\":\"93\",\"competitors\":[{\"competitorName\":\"{\\\"1\\\":\\\"David Barclay\\\",\\\"512\\\":\\\"David Barclay\\\"}\",\"role\":1,\"isBetter\":1,\"competitorId\":22777},{\"competitorName\":\"{\\\"1\\\":\\\"Alex Hunt\\\",\\\"512\\\":\\\"Alex Hunt\\\"}\",\"role\":2,\"isBetter\":0,\"competitorId\":168146}],\"leagueId\":20846,\"H5HomePageAction\":-1,\"marketCnt\":11,\"regionNum\":1771,\"CompetitorPeriodScore\":\"{\\\"1\\\":{\\\"0_0\\\":\\\"1\\\"},\\\"2\\\":{\\\"0_0\\\":\\\"1\\\"}}\",\"matchId\":4014540,\"preListAction\":-1,\"isVisible\":1,\"minute\":0,\"sportId\":3,\"prty\":99999,\"marketTypes\":[{\"name\":\"{\\\"1\\\":\\\"Result\\\",\\\"512\\\":\\\"标准盘\\\"}\",\"marketIds\":[702],\"marketId\":702},{\"name\":\"{\\\"1\\\":\\\"Handicap\\\",\\\"512\\\":\\\"局数让分盘\\\"}\",\"marketIds\":[2,992],\"marketId\":2},{\"name\":\"{\\\"1\\\":\\\"Handicap\\\",\\\"512\\\":\\\"附加局数让分盘\\\"}\",\"marketIds\":[2,992],\"marketId\":992}],\"isInLive\":true,\"score1\":1,\"matchPeriod\":\"3rd set\",\"inLiveListAction\":-1,\"status\":0},\"partnerData\":[]},\"head\":{\"dataType\":1}}", DgtKafkaDto.class);
        MatchItemProto.MatchItem.Builder builder = MatchItemProto.MatchItem.newBuilder();
        MatchItemProto.Competitor.Builder builder2 = MatchItemProto.Competitor.newBuilder();
        MatchItemProto.MarketType.Builder builder3 = MatchItemProto.MarketType.newBuilder();
        Map<String, Descriptors.FieldDescriptor> fields = new HashMap<>();
        Map<String, Descriptors.FieldDescriptor> fields2 = new HashMap<>();
        Map<String, Descriptors.FieldDescriptor> fields3 = new HashMap<>();
        builder.getDescriptorForType().getFields().stream().forEach(x -> fields.put(x.getName(), x));
        builder2.getDescriptorForType().getFields().stream().forEach(x -> fields2.put(x.getName(), x));
        builder3.getDescriptorForType().getFields().stream().forEach(x -> fields3.put(x.getName(), x));
        for (String key : dto.getContent().getBaseData().keySet()) {
            if (key != "competitors" && key != "marketTypes" && fields.containsKey(key)) {
                builder.setField(fields.get(key), dto.getBaseDatas(key));
            }
        }
        if (dto.getContent().getBaseData().containsKey("competitors")) {
            List<Map<String, Object>> competitors = (List<Map<String, Object>>) dto.getBaseDatas("competitors");
            for (Map<String, Object> competitor : competitors) {
                for (String key : competitor.keySet()) {
                    builder2.setField(fields2.get(key), competitor.get(key));
                }
                builder.addCompetitors(builder2.build());
                builder2.clear();
            }
        }
        if (dto.getContent().getBaseData().containsKey("marketTypes")) {
            List<Map<String, Object>> marketTypes = (List<Map<String, Object>>) dto.getBaseDatas("marketTypes");
            for (Map<String, Object> marketType : marketTypes) {
                for (String key : marketType.keySet()) {
                    builder3.setField(fields3.get(key), marketType.get(key));
                }
                builder.addMarketTypes(builder3.build());
                builder3.clear();
            }
        }
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        Long start = System.currentTimeMillis();
        // 创建SubscribeReqProto.SubscribeReq对象
        MatchItemProto.MatchItem req = createMatchItem();
        // 输出编码前的SubscribeReqProto.SubscribeReq对象req的值
        System.out.println("Before encode : " + req.toString());
        System.out.println("Before encode size: " + req.toString().length());
        System.out.println("after encode size: " + req.toByteArray().length);
        // 获取解码后的SubscribeReqProto.SubscribeReq对象
        MatchItemProto.MatchItem req2 = decodeMatch(encode(req));
        // 输出解码后的SubscribeReqProto.SubscribeReq对象req2的值
        System.out.println("After decode : " + req2.toString());
        // 比较req与req2的值是否相等 --- true说明
        System.out.println("Assert equal : -->" + req2.equals(req));

        System.out.println("cost time: " + (System.currentTimeMillis() - start) / 1000 + "ms");

/*        String code = ProtobufIDLGenerator.getIDL(MatchItemProto0.class);
        System.out.println(code);
        code = ProtobufIDLGenerator.getIDL(SimpleTypeTest.class);*/
        //System.out.println(code);
    }
}
