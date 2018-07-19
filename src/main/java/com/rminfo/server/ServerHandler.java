package com.rminfo.server;

import com.rminfo.module.ReplyCode;
import com.rminfo.module.RequestCode;
import com.rminfo.util.BytesUtil;
import com.rminfo.util.JdbcUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Administrator on 2016/5/14.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter { // (1)
    private int commandCode;

    private ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        group.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyChannelMap.remove((SocketChannel)ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                byte[] req = new byte[in.readableBytes()];
                in.readBytes(req);
                String body = BytesUtil.bytesToHexString(req);
                /**
                 * 获取在线数量
                 */
                if(Objects.equals("FFFF", body)) {
                    ByteBuf encoded = ctx.alloc().buffer();
                    encoded.writeBytes(BytesUtil.intToByteArray(NettyChannelMap.getNum()));
                    ctx.writeAndFlush(encoded);
                    return;
                }
                //插入数据库
                RequestCode hexCode = new RequestCode();
                if(null!=body&& !Objects.equals("", body) &&body.length()>28){
                    hexCode.setLength(body.substring(0, 4));
                    hexCode.setCarId(body.substring(4, 16));
                    hexCode.setVersion(body.substring(16, 18));
                    hexCode.setCommandCode(body.substring(18, 22));
                    hexCode.setCommandId(body.substring(22, 24));
                    hexCode.setReserved(body.substring(24, 28));
                    hexCode.setContent(body.substring(28));
                }

                //判断sim卡是否在库中
                boolean flag = JdbcUtil.selectSim(hexCode.getCarId());
                if (!flag){
                    System.out.println("此sim卡号不在数据库中");
                    return;
                }

                /**
                 * 下发命令
                 */
                if(Objects.equals("FFFF", hexCode.getReserved())) {
                    SocketChannel channel = (SocketChannel) NettyChannelMap.get(hexCode.getCarId());
                    if (channel != null) {
                        System.out.println("当前客户端数量："+NettyChannelMap.getNum());
                        System.out.println("下发码："+body);
                        // 在当前场景下，发送的数据必须转换成ByteBuf数组
                        ByteBuf encoded = null;
                        if (body != null) {
                            encoded = ctx.alloc().buffer(4 * body.length());
                        }
                        if (encoded != null) {
                            encoded.writeBytes(BytesUtil.hexStringToBytes(body));
                        }
                        channel.writeAndFlush(encoded);
                    }else{
                        System.out.println("当前无客户端在线");
                    }
                    JdbcUtil.insertBody(body);
                }else {
                    System.out.println("----------客户端"+hexCode.getCarId()+" 发送报文------------");
                    NettyChannelMap.add(hexCode.getCarId(),(SocketChannel)ctx.channel());
                    JdbcUtil.insertBody(body);
                }
                System.out.println("时间:" + new Date());
                System.out.println("请求码:" + body);
                //发送给kafka
                String result="00";
                boolean kafkaResult =  KafKaProducer.inBoundKafka("wufeng", "test", body);
//                if(!kafkaResult){
//                    result="01";
//                }
                if(commandCode>65535){
                    commandCode=0;
                }
                // 向客户端发送消息
                ReplyCode replyCode = new ReplyCode();
                replyCode.setCarId(hexCode.getCarId());
                replyCode.setVersion(hexCode.getVersion());
                replyCode.setCommandCode(BytesUtil.padLeft(Integer.toHexString(commandCode),4));
                replyCode.setCommandId("04");
                replyCode.setReserved(hexCode.getReserved());
                replyCode.setTerminalCommandCode(hexCode.getCommandCode());
                replyCode.setTerminalCommandId(hexCode.getCommandId());
                replyCode.setResult(result);
                String length = String.format("%04d",Integer.parseInt(Integer.toHexString(replyCode.toString().length()))/2);
                replyCode.setLength(length);
                String response = replyCode.toString();
                System.out.println("响应码："+response);
                System.out.println();
                // 在当前场景下，发送的数据必须转换成ByteBuf数组
                ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
                encoded.writeBytes(BytesUtil.hexStringToBytes(response));
                ctx.write(encoded);
                ctx.flush();
                commandCode++;

            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        System.out.println("----------客户端断开------------");
        cause.printStackTrace();
        ctx.close();
    }
}