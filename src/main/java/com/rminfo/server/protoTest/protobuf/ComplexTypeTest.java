package com.rminfo.server.protoTest.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * Created by junior on 11:45 2018/7/16.
 */
public class ComplexTypeTest {

    @Protobuf
    private String name;

    @Protobuf
    private SimpleTypeTest simpleTypeTest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleTypeTest getSimpleTypeTest() {
        return simpleTypeTest;
    }

    public void setSimpleTypeTest(SimpleTypeTest simpleTypeTest) {
        this.simpleTypeTest = simpleTypeTest;
    }
}
