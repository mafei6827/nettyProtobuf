package com.rminfo.server.protoTest.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junior on 17:17 2018/7/9.
 */
public class TestSubscribeReqProto {

    /**
     * 编码方法，将SubscribeReqProto.SubscribeReq对象编码为字节数组
     * @param req
     * @return
     */
    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    /**
     * 解码方法，将字节数组解码为SubscribeReqProto.SubscribeReq对象
     * @param body
     * @return
     * @throws InvalidProtocolBufferException
     */
    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    /**
     * 通过SubscribeReqProto.SubscribeReq的静态方法newBuilder创建SubscribeReqProto.SubscribeReq的
     * Builder实例，通过Builder构建器对SubscribeReq的属性进行设置，对于集合类型，通过addAllxxx()方法
     * 可以将集合对象设置到对应到的属性中，最后通过builder的build方法返回设置好属性的SubscribeReqProto.SubscribeReq对象
     * @return
     */
    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("xpleaf");
        builder.setProductName("Netty Book");
        List<String> address = new ArrayList<>();
        address.add("NanJing YuHuaTai");
        address.add("BeiJing LiuLiChange");
        address.add("ShenZhen HongShuLin");
        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        // 创建SubscribeReqProto.SubscribeReq对象
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        // 输出编码前的SubscribeReqProto.SubscribeReq对象req的值
        System.out.println("Before encode : " + req.toString());
        System.out.println();
        System.out.println("Before encode size: " + req.toString().length());
        System.out.println("after encode size: " + req.toByteArray().length);
        System.out.println("after encode size: " + new String(req.toByteArray()));
        // 获取解码后的SubscribeReqProto.SubscribeReq对象
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        // 输出解码后的SubscribeReqProto.SubscribeReq对象req2的值
        System.out.println("After decode : " + req.toString());
        // 比较req与req2的值是否相等 --- true说明
        System.out.println("Assert equal : -->" + req2.equals(req));
    }
}
