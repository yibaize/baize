package org.baize.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.baize.server.manager.RequestDecoderManager;
import org.baize.server.manager.ResponseEncoderManager;
import org.baize.server.manager.ServerHandlerManager;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public class GameServer {
    private static final EventLoopGroup BOSS_GROUP = new NioEventLoopGroup(1);
    private static final EventLoopGroup WORKER_GROUP = new NioEventLoopGroup();
    public static void start(int port){
        //配置服务器端的nio
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(BOSS_GROUP, WORKER_GROUP)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,2048)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new RequestDecoderManager());
                            ch.pipeline().addLast(new ResponseEncoderManager());
                            ch.pipeline().addLast(new ServerHandlerManager());
                        }
                    });
            //绑定端口
            ChannelFuture f = b.bind(port).sync();
            System.err.println("*****服务器启动*****");
            f.channel().closeFuture().sync();//等待服务端监听关闭
        }catch (Exception e){
            System.err.println("xxxx服务器启动失败xxxx");
            e.printStackTrace();
        }finally {
            //优雅退出线程
            BOSS_GROUP.shutdownGracefully();
            WORKER_GROUP.shutdownGracefully();
        }
    }
}
