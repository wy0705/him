package com.lmxdawn.him.api.phone;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SMSUtil {
    public static String sendSMS(HttpServletRequest request, String phoneNumber) {
        String reStr = ""; //定义返回值
        // 短信应用SDK AppID  1400开头
//        int appid = 1400****  ;
        int appid =0;
        // 短信应用SDK AppKey
        String appkey = " ********* ";
        // 短信模板ID，需要在短信应用中申请
//        int templateId = ****** ;
        int templateId=0;
        // 签名，使用的是签名内容，而不是签名ID
        String smsSign = "短信验证";
        //随机生成四位验证码的工具类
        String code = KeyUtil.keyUtils();
        try {
            //参数，一定要对应短信模板中的参数顺序和个数，
            String[] params = {code};
            //创建ssender对象
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            //发送
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,templateId, params, smsSign, "", "");
            if(result.result!=0){
                reStr = "error";
            }
            // 签名参数未提供或者为空时，会使用默认签名发送短信
//            HttpSession session = request.getSession();
//            //JSONObject存入数据
//            Map<String,String> map = new HashMap<String, String>();
//            map.put("Code", code);//存入验证码
//            map.put("createTime", System.currentTimeMillis());//存入发送短信验证码的时间
            // 将验证码和短信发送时间码存入SESSION
            request.getSession().setAttribute("Code", code);
            request.getSession().setAttribute("createTime", System.currentTimeMillis());
            reStr = "success";
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return reStr;
    }

}

