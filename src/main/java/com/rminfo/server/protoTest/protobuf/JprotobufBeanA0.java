package com.rminfo.server.protoTest.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.Map;

/**
 * Created by junior on 10:44 2018/7/16.
 */
public class JprotobufBeanA0 {
    @Protobuf
    public String name;
    @Protobuf
    public Integer id;
    @Protobuf
    public String email;
    @Protobuf
    public Double doubleF;
    @Protobuf
    public Float floatF;
    @Protobuf
    public byte[] bytesF;
    @Protobuf
    public Boolean boolF;
    @Protobuf(fieldType = FieldType.MAP, order=10, required = false)
    public Map<String,String> map;
    @Protobuf
    public ComplexTypeTest complexTypeTest;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public ComplexTypeTest getSimpleTypeTest() {
        return complexTypeTest;
    }

    public void setSimpleTypeTest(ComplexTypeTest complexTypeTest) {
        this.complexTypeTest = complexTypeTest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getDoubleF() {
        return doubleF;
    }

    public void setDoubleF(Double doubleF) {
        this.doubleF = doubleF;
    }

    public Float getFloatF() {
        return floatF;
    }

    public void setFloatF(Float floatF) {
        this.floatF = floatF;
    }

    public byte[] getBytesF() {
        return bytesF;
    }

    public void setBytesF(byte[] bytesF) {
        this.bytesF = bytesF;
    }

    public Boolean getBoolF() {
        return boolF;
    }

    public void setBoolF(Boolean boolF) {
        this.boolF = boolF;
    }
}
