package com.rminfo.protobuf;

/**
 * Created by junior on 11:02 2018/1/8.
 */
public class TestJsonBean {
    private int senderId;
    /**
     * <pre>
     *参数类型 参数名称=字段编码值
     * </pre>
     *
     * <code>int32 senderId = 1;</code>
     */

    private int receiverId;
    /**
     * <pre>
     * 接受人ID
     * </pre>
     *
     * <code>int32 receiverId = 2;</code>
     */
    private volatile Object senderName;

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public Object getSenderName() {
        return senderName;
    }

    public void setSenderName(Object senderName) {
        this.senderName = senderName;
    }
}
