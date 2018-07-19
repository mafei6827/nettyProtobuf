package com.rminfo.server.protoTest;

import com.rminfo.server.protoTest.protobuf.JprotobufBeanA;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by junior on 17:51 2018/7/13.
 */
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for(int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    /**
     * 构建SubscribeReqProto.SubscribeReq对象
     * @param i
     * @return
     */
    private JprotobufBeanA.JprotobufBean subReq(int i) {
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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Service accept server subscribe response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
