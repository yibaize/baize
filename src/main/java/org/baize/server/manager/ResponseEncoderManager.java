package org.baize.server.manager;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
public class ResponseEncoderManager extends MessageToByteEncoder<Response> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Response response, ByteBuf buffer) throws Exception {
        buffer.writeInt(-777888);//包头:请使用一个不常用到的int类型数据
        buffer.writeShort(response.getId());
        if (response.getDataLength() <= 0 || response.getData() == null) {
            buffer.writeShort(response.getDataLength());
        } else {
            buffer.writeShort(response.getDataLength());
            buffer.writeBytes(response.getData());//数据
        }
    }
}
