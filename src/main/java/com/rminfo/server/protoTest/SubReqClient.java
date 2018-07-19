package com.rminfo.server.protoTest;

import com.rminfo.server.protoTest.protobuf.JprotobufBeanA;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by junior on 17:51 2018/7/13.
 */
public class SubReqClient {
    public void connect(String host, int port) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    // 设置TCP连接超时时间
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 添加ProtobufVarint32FrameDecoder，主要用于Protobuf的半包处理
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            // 添加ProtobufDecoder解码器，它的参数是com.google.protobuf.MessageLite
                            // 实际上就是要告诉ProtobufDecoder需要解码的目标类是什么，否则仅仅从字节数组中是
                            // 无法判断出要解码的目标类型信息的（客户端需要解析的是服务端请求，所以是Resp）
                            ch.pipeline().addLast(new ProtobufDecoder(JprotobufBeanA.JprotobufBean.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            // 添加ProtobufEncoder编码器，这样就不需要对SubscribeResp进行手工编码
                            ch.pipeline().addLast(new ProtobufEncoder());
                            // 添加业务处理handler
                            ch.pipeline().addLast(new SubReqClientHandler());
                        }
                    });
            // 发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();

            // 等待客户端链路关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args != null && args.length > 0) {
            try {
                port = Integer.valueOf(port);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new SubReqClient().connect("localhost", port);
    }
}
