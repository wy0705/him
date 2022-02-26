package com.lmxdawn.him.common.vo.req;

import java.awt.*;
import java.util.UUID;
public abstract class BaseReqVO {

    /**
     * 唯一请求号
     */
    private String reqNo;

    /**
     * 请求的时间戳
     */
    private Long timeStamp;

    private MessageType type;



    public BaseReqVO() {
        this.reqNo = UUID.randomUUID().toString();
        this.timeStamp = System.currentTimeMillis();
        this.type=type;
    }

    public String getReqNo() {
        return reqNo;
    }

    public  void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public abstract void isType();
}
