package com.rminfo.protobuf.item;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.Map;

/**
 * Created by junior on 11:42 2018/7/17.
 */
public class PrimeArgumentProto {

    @Protobuf
    public Byte correspondingMarketId;
    @Protobuf
    public Integer mSort;
    @Protobuf
    public String marketName;
    @Protobuf
    public Integer marketId;
    @Protobuf
    public String priArgument;
    @Protobuf(fieldType = FieldType.MAP)
    public Map<String,OptionProto> optionList;
}
