package com.rminfo.protobuf.item;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * Created by junior on 11:50 2018/7/17.
 */
public class OptionProto {
    @Protobuf
    public Byte partnerId;
    @Protobuf
    public Byte isVisible;
    @Protobuf
    public Byte optionNum;
    @Protobuf
    public Integer marketId;
    @Protobuf
    public Integer oddsId;
    @Protobuf
    public Integer matchPeriodId;
    @Protobuf
    public String oddsValue;
    @Protobuf
    public String marketFullId;
    @Protobuf
    public String optionName;
    @Protobuf
    public String argument;
}
