package com.rminfo.server.protoTest.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * Created by junior on 11:45 2018/7/16.
 */
public class SimpleTypeTest {

    @Protobuf
    private String name;

    @Protobuf
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
