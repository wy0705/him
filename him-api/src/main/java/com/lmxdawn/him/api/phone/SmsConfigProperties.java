package com.lmxdawn.him.api.phone;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmsConfig
 * @Description TODO
 * @Author 华达州
 * @Date 2021/8/6 13:58
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "smsconfig")
public class SmsConfigProperties {

    private int appId;

    private String appKey;

    private int templateId;

    private String smsSign;

    private int invalidTime;

}

