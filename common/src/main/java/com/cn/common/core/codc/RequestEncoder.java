package com.cn.common.core.codc;

import com.cn.common.core.module.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class RequestEncoder extends OneToOneEncoder {
    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object msg) throws Exception {
        Request message = (Request) msg;
        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();
        //包头
        channelBuffer.writeInt(ConstantValue.HEADER_FLAG);
        //module
        channelBuffer.writeShort(message.getModule());
        //cmd
        channelBuffer.writeShort(message.getCmd());
        //长度
        int length = message.getData()==null? 0 : message.getData().length;
        if (length <= 0){
            channelBuffer.writeInt(length);
        }else{
            channelBuffer.writeInt(length);
            channelBuffer.writeBytes(message.getData());
        }
        return channelBuffer;
    }
}
