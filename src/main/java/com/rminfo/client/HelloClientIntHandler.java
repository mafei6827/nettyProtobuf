package com.rminfo.client;

import com.rminfo.util.BytesUtil;
import com.rminfo.util.JdbcUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HelloClientIntHandler.class);
    //SIM卡号（3405885067，3451806520,3400071909）
    private static final String SIM    = "4751714353";
    //远程升级06指令；
    private static final String UPDATE = "005907"+SIM+"02004E06FFFF006674703A2F2F56656869636C653A56656869636C65232A403230322E3130322E39302E3137393A32312F54482F59534C2F544841475F57464E4A30315F31315F3137303331332E62696E";
    //打开GPS，设置为30秒01指令；
    private static final String GPS    = "001007"+SIM+"02004E01FFFF001E";

    //切换远程地址；
    private static final String CHANGE_HOST    = "001507"+SIM+"02004E02FFFF000804771791E2";
  
    // 接收server端的消息，并打印出来  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        logger.info("HelloClientIntHandler.channelRead");  
        ByteBuf result = (ByteBuf) msg;  
        byte[] result1 = new byte[result.readableBytes()];  
        result.readBytes(result1);
        String body = BytesUtil.bytesToHexString(result1)+"";
        System.out.println("服务器响应:" +body);
        JdbcUtil.selectDate(SIM);
        result.release();
    }  

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("HelloClientIntHandler.channelActive");
        //远程升级06指令
        String message = CHANGE_HOST;
        byte[] bytes = BytesUtil.hexStringToBytes(message);
        ByteBuf encoded = ctx.alloc().buffer(4 * message.length());
        encoded.writeBytes(bytes);
            ctx.write(encoded);
            ctx.flush();
    }
}  