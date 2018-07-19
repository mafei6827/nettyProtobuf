package com.rminfo.protobuf.item;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.List;

/**
 * Created by junior on 10:27 2018/7/19.
 */
public class MarketTypes0 {
    @Protobuf
    public Integer marketId;
    @Protobuf
    public String name;
    @Protobuf
    public List<Integer> marketIds;

}
