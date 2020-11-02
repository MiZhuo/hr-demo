package fun.mizhuo.hrserver.controller.common;

import com.alibaba.fastjson.JSONObject;
import fun.mizhuo.hrserver.model.ChatMsg;
import fun.mizhuo.hrserver.model.Hr;
import fun.mizhuo.hrserver.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @author: Mizhuo
 * @time: 2020/11/1 3:04 下午
 * @description: websocket Controller
 */
@Api(value = "WsController",tags = {"websocket接口"})
@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "发送websocket消息")
    @MessageMapping("/ws/chat")
    public void handleMessage(Authentication authentication, ChatMsg chatMsg){
        Hr hr = (Hr) authentication.getPrincipal();
        String online = String.valueOf(redisUtils.get(hr.getUsername()));
        if(StringUtils.isEmpty(online)){
            redisUtils.set(hr.getUsername(),"online");
            redisUtils.expire(hr.getUsername(),30 * 60 * 1000);
        }
        chatMsg.setFrom(hr.getUsername());
        chatMsg.setFromName(hr.getName());
        chatMsg.setDate(new Date(System.currentTimeMillis()));
        if(redisUtils.get(chatMsg.getTo()) == null){
            //发送给离线好友,将消息存放在Redis
            String key = chatMsg.getTo() + "#" + hr.getUsername();
            String content = JSONObject.toJSONString(chatMsg);
            redisUtils.lSet(key,content);
            return;
        }
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/chat",chatMsg);
    }

}
