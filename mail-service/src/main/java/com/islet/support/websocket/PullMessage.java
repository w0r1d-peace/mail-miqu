package com.islet.support.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tangJM.
 * 提示消息（右下角提示框信息）
 */
@Slf4j
@ServerEndpoint(value = "/pull/message/{userId}")
@Component
public class PullMessage {

    /**
     * 静态容器，记录每个客户端连接时，产生对应的ManagerWebSocket对象。
     * concurrent包的线程安全Set。
     */
    private static ConcurrentHashMap<Long, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * TODO: 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("userId")Long userId, Session session) {
        sessionMap.put(userId, session);
    }

    /**
     * TODO: 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userId")Long userId) {
        Session session = sessionMap.get(userId);
        if (session != null) {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sessionMap.remove(userId);
    }

    /**
     * TODO: 发生错误时调用
     */
    @OnError
    public void onError(Throwable error) {
        log.info("发生错误");
        error.printStackTrace();
    }

    /**
     * TODO: 给客户端发消息
     */
    public void sendMessage(Long userId, String message) {
        Session session = sessionMap.get(userId);
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}