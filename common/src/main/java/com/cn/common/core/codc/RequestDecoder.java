package com.cn.common.core.codc;

import com.cn.common.core.module.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class RequestDecoder extends FrameDecoder {

    public static int BASE_LENTH = 4 + 2 + 2 +4;

    /**
     * @param channelHandlerContext
     * @param channel
     * @param channelBuffer
     * @return
     * @throws Exception
     */
    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        if (channelBuffer.readableBytes() >= BASE_LENTH){
            //第一个可读数据包的起始位置
            int beginIndex;

            while(true){
                //包头开始游标点
                beginIndex = channelBuffer.readerIndex();

                //标记初始读游标位置
                channelBuffer.markReaderIndex();
                if (channelBuffer.readInt() == ConstantValue.HEADER_FLAG){
                    break;
                }
                //未读到包头标识略过一个字节
                channelBuffer.resetReaderIndex();
                channelBuffer.readByte();

                if (channelBuffer.readableBytes() < BASE_LENTH){
                    return null;
                }
            }
            short module = channelBuffer.readShort();
            short cmd = channelBuffer.readShort();

            //读取数据长度
            int lenth = channelBuffer.readInt();
            if(lenth < 0 ){
                channel.close();
            }

            //数据包还没到齐
            if(channelBuffer.readableBytes() < lenth){
                channelBuffer.readerIndex(beginIndex);
                return null;
            }

            //读数据部分
            byte[] data = new byte[lenth];
            channelBuffer.readBytes(data);

            Request message = new Request();
            message.setModule(module);
            message.setCmd(cmd);
            message.setData(data);
            //解析出消息对象，继续往下面的handler传递
            return message;
        }
        return null;
    }
}
