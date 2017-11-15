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
import org.baize.server.manager.RequestDecoderManager;
import org.baize.server.manager.ResponseEncoderManager;
import org.baize.server.manager.ServerHandlerManager;
import org.baize.utils.DateUtils;
/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public final class GameServer {
    private static final EventLoopGroup BOSS_GROUP = new NioEventLoopGroup(1);
    private static final EventLoopGroup WORKER_GROUP = new NioEventLoopGroup();
    private static final int PORT = 7788;
    public static final void start(){
        //配置服务器端的nio
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(BOSS_GROUP, WORKER_GROUP)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,100)
                    .childOption(ChannelOption.SO_KEEPALIVE, false)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ResponseEncoderManager());
                            ch.pipeline().addLast(new RequestDecoderManager());
                            ch.pipeline().addLast(new ServerHandlerManager());
                        }
                    });
            //绑定端口
            ChannelFuture f = b.bind(PORT).sync();
            System.err.println(DateUtils.currentTime() + ":---------------服务器启动成功------------------");
            //new Error(GameServer.class).debug(System.currentTimeMillis() + ":---------------服务器启动成功------------------");
            f.channel().closeFuture().sync();//等待服务端监听关闭
        }catch (Exception e){
            //new Error(this.getClass()).debug(System.currentTimeMillis() + ":---------------服务器启动成功------------------");
        }finally {
            //优雅退出线程
            BOSS_GROUP.shutdownGracefully();
            WORKER_GROUP.shutdownGracefully();
        }
    }
}
