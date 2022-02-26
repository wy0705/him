package com.lmxdawn.him.api.ws;

import com.google.gson.Gson;
import com.lmxdawn.him.api.constant.WSResTypeConstant;
import com.lmxdawn.him.api.constant.WSReqTypeConstant;
import com.lmxdawn.him.api.db.service.UserService;
import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.api.vo.req.UserLoginVo;
import com.lmxdawn.him.api.vo.res.ErrorVo;
import com.lmxdawn.him.api.vo.res.URLVo;
import com.lmxdawn.him.common.protobuf.WSBaseReqProtoOuterClass;
import com.lmxdawn.him.common.protobuf.WSBaseResProtoOuterClass;
import com.lmxdawn.him.common.vo.req.BaseReqVO;
import io.netty.channel.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

@ChannelHandler.Sharable
@Slf4j
public class WSServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Autowired
    private UserService userService;

    /**
     * 取消绑定
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 可能出现业务判断离线后再次触发 channelInactive
        log.warn("触发 channelInactive 掉线![{}]", ctx.channel().id());
        userOffLine(ctx);
    }

    /**
     * 心跳检查
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            // 读空闲
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                // 关闭用户的连接
                userOffLine(ctx);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    /**
     * 用户下线
     */
    private void userOffLine(ChannelHandlerContext ctx) throws IOException {
        WSSocketHolder.remove(ctx.channel());
        ctx.channel().close();
    }

    /**
     * 读到客户端的内容 （这里只做心跳检查）
     * @param ctx
     * @param msg
     * @throws Exception
     */
    /*@Override
    protected void channelRead0(ChannelHandlerContext ctx, WSBaseReqProtoOuterClass.WSBaseReqProto msg) throws Exception {

        String sid = msg.getSid();
        long uid = msg.getUid();
        Integer type = msg.getType();
        switch (type) {
            case WSReqTypeConstant.LOGIN: // 登录类型
                log.info("用户登录");
                userLogin(ctx, uid, sid);
                break;
            case WSReqTypeConstant.PING: // 心跳
                log.info("客户端心跳");
                break;
            default:
                log.info("未知类型");
        }

    }*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame webSocketFrame) throws Exception {

        UserLoginVo userLoginVo=new Gson().fromJson(webSocketFrame.text(), UserLoginVo.class);
       /* if (message == null) {
            sendMessageByChannel(channelHandlerContext.channel(), new Message(channelHandlerContext.channel().id().asShortText(), "消息错误", System.currentTimeMillis(), MessageType.CHAT_MSG.name()));
            return;
        } else {
            //改名字
            if (MessageType.CHANGE_NAME.name().equals(message.getMessageType())) {
                nameMap.put(channelHandlerContext.channel().id().asShortText(), message.getContent());
                sendMessageForAll(new Message("", channelHandlerContext.channel().id().asShortText() + "_" + message.getContent(), System.currentTimeMillis(), MessageType.CHANGE_NAME.name()));
            } else {
                ChannelId channelId = channelIdMap.get(message.getId());
                if (channelId == null) {
                    sendMessageByChannel(channelHandlerContext.channel(), new Message(channelHandlerContext.channel().id().asShortText(), "对方已下线", System.currentTimeMillis(), MessageType.CHAT_MSG.name()));
                } else {
                    sendMessageByChannel(channelGroup.find(channelId), message);
                }
            }

        }
        System.out.println(channelHandlerContext.channel().remoteAddress() + "--->" + message.getContent() + "--->" + message.getTimestamp());*/

        if (userLoginVo==null){
            sendMessageByChannel(ctx.channel(), new ErrorVo("消息错误"));
            return;
        }else {
            if (userService.login(userLoginVo.getUsername(),userLoginVo.getPassword())==0){
                sendMessageByChannel(ctx.channel(),new ErrorVo("登陆失败"));
                System.out.println("登陆失败");
            }else {
                System.out.println("登陆成功");
                sendMessageByChannel(ctx.channel(),new URLVo(""));
            }
        }
    }

    private void sendMessageByChannel(Channel channel, BaseReqVO baseReqVO) {
        channel.writeAndFlush(new TextWebSocketFrame(new Gson().toJson(baseReqVO)));
    }

    private void userLogin(ChannelHandlerContext ctx, Long uid, String sid) throws IOException {
        if (!UserLoginUtils.checkToken(uid, sid)) {
            log.info("非法登录: {}, {}", uid, sid);
            // 登录异常, 发送下线通知
            WSBaseResProtoOuterClass.WSBaseResProto wsBaseResProto = WSBaseResProtoOuterClass.WSBaseResProto.newBuilder()
                    .setType(WSResTypeConstant.LOGIN_OUT)
                    .setCreateTime(new Date().toString())
                    .build();
            // 发送下线消息
            ctx.channel().writeAndFlush(wsBaseResProto);
            return;
        }

        // 判断是否在线, 如果在线, 则剔除当前在线用户
        Channel channel = WSSocketHolder.get(uid);
        // 如果不是第一次登陆, 并且 客户端ID和当前的不匹配, 则通知之前的客户端下线
        if (channel != null && !ctx.channel().id().equals(channel.id())) {
            WSBaseResProtoOuterClass.WSBaseResProto wsBaseResProto = WSBaseResProtoOuterClass.WSBaseResProto.newBuilder()
                    .setType(WSResTypeConstant.WS_OUT)
                    .setCreateTime(new Date().toString())
                    .build();
            // 发送下线消息
            channel.writeAndFlush(wsBaseResProto);
        }

        // 加入 在线 map 中
        WSSocketHolder.put(uid, ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if ("Connection reset by peer".equals(cause.getMessage())) {
            log.error("连接出现问题");
            return;
        }

        log.error(cause.getMessage(), cause);
    }

}
