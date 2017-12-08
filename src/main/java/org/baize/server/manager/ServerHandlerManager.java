package org.baize.server.manager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.baize.server.message.CommandCode;
import org.baize.server.message.TcpHandler;
import org.baize.server.session.ISession;
import org.baize.server.session.SessionImpl;
import org.baize.server.session.SessionManager;

public class ServerHandlerManager extends SimpleChannelInboundHandler<Request>{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//客户端在
		System.err.println("用户上线"+ctx.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("用户下线"+ctx.channel().remoteAddress());
		outLine(ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println("用户下线"+ctx.channel().remoteAddress());
		outLine(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
		//将消息发送到消息分发
		handlerMessage(ctx.channel(),request);
	}
	/**消息分发器*/
	private void handlerMessage(Channel channel, Request request){
		ISession session = new SessionImpl(channel);
		TcpHandler.messageRecieve(session,request);
	}
	private void outLine(Channel channel){
		ISession session = new SessionImpl(channel);
		Request r = new Request((short) CommandCode.Out_line,new Msg(""));
		TcpHandler.messageRecieve(session,r);
	}
}
