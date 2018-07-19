package com.rminfo.protobuf;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by junior on 10:42 2018/1/8.
 */

public class MainTest {

    public static void main(String[] args) {
        TestProto.UserInfo info = TestProto.UserInfo.newBuilder().setReceiverId(1).setReceiverName("junior").setSenderId(2).build();
        System.out.println(info);
        byte[] bytes = info.toByteArray();
        System.out.println(bytes.length);
        try {
            TestProto.UserInfo info2 = TestProto.UserInfo.parseFrom(bytes);
            System.out.println(info2);
            System.out.println(info.equals(info2));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }


        TestJsonBean jsonBean = new TestJsonBean();
        jsonBean.setReceiverId(1);
        jsonBean.setSenderId(2);
        jsonBean.setSenderName("junior");
        System.out.println(JSON.toJSONString(jsonBean).getBytes().length);

        ListProto.Father father = ListProto.Father.newBuilder().addSons(ListProto.Son.newBuilder().setName("abc")).addSons(ListProto.Son.newBuilder().setName("ABC")).build();
        System.out.println(father);
        ByteString bytes1 = father.toByteString();
        System.out.println(bytes1.toStringUtf8());

        String str = "abcd";
        System.out.println(str.getBytes());
    }
}
