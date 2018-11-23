package com.suchaos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;

import static com.suchaos.util.WebSocketUtils.ONLINE_USER_SESSIONS;
import static com.suchaos.util.WebSocketUtils.sendMessageAll;

/**
 * ChatRoomServerEndpoint
 *
 * @author suchao
 * @date 2018/11/23
 */
@Slf4j
@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        ONLINE_USER_SESSIONS.put(username, session);
        String message = "欢迎用户[" + username + "] 来到聊天室！";
        log.info("用户登录: " + message);
        sendMessageAll(message);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        log.info("发送消息：" + message);
        sendMessageAll("用户[" + username + "] : " + message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        ONLINE_USER_SESSIONS.remove(username);
        sendMessageAll("用户[" + username + "] 已经离开聊天室了！");
        try {
            session.close();
        } catch (IOException e) {
            log.error("onClose error: ", e);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            log.error("onError exception: ", e);
        }
        log.info("Throwable msg: " + throwable.getMessage());
    }
}
