package com.cn.common.core.session;

public interface Session {

    /**
     * 会话绑定对象
     */
    Object getAttachment();

    /**
     * 绑定对象
     * @param attachment
     */
    void setAttachment(Object attachment);

    /**
     * 移除绑定对象
     */
    void removeAttachment();

    /**
     * 向会话中写入消息
     * @param message
     */
    void write(Object message);

    /**
     * 判断会话是否在连接中
     */
    boolean isConnected();

    /**
     * 关闭
     */
    void close();
}
