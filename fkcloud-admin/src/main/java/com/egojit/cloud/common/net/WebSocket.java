package com.egojit.cloud.common.net;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import  com.egojit.cloud.common.utils.SpringUtil;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Controller
@ServerEndpoint("/api/v1/admin/ws/{biz}/{bizId}")
public class WebSocket {
    private static Logger log = LoggerFactory.getLogger(WebSocket.class);
    //    private static ConcurrentMap<String, CopyOnWriteArrayList<WebSocket>> webSocketMap = new ConcurrentHashMap<>();
    private static CopyOnWriteArrayList<WebSocket> webSocketMap = new CopyOnWriteArrayList<>();
    private Session session;
    private String server;
    private String id;//消息的地址:业务区分.key，例如192.168.11.18@bz@sessionid


    /**
     * webSocket 处理器
     */

    private WebSocketHandler webSocketHandler;


    @OnOpen
    public void onOpen(@PathParam("biz") String biz, @PathParam("bizId") String bizId, Session session) {
        webSocketHandler = SpringUtil.getBean(biz + "WebSocketHandler", WebSocketHandler.class);
        //参数合法性check
        if (StringUtils.isBlank(biz) || StringUtils.isBlank(biz)) {
            String msg = "websocket连接参数不合法，server=" + server + ",key=" + biz;
            log.error(msg);
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, msg));
            } catch (IOException e) {
                log.error("关闭websocket连接异常:" + e.toString());
            }
        }
        //保存websocket连接
        this.id = server + "@" + biz + "@" + session.getId();
        this.server = server;
        this.session = session;
        webSocketMap.add(this);
//        webSocketHandler.init(this, bizId);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
//        CopyOnWriteArrayList<WebSocket> webSocketList = webSocketMap.get(this.server);
//        if (webSocketList != null) {
//            webSocketList.remove(this);
//            if (webSocketList.isEmpty()) {
//                webSocketMap.remove(this.server);
//            }
//        }
        webSocketHandler.destory();
        webSocketMap.remove(this.server);
        log.info("onClose: address=" + this.server + ",id=" + session.getId() + ",reason=" + closeReason.getReasonPhrase());
    }

    //连接错误时执行
    @OnError
    public void onError(Throwable t) {
        log.error("websocket onError:" + t.toString());
        if(webSocketHandler!=null) {
            webSocketHandler.destory();
        }
    }


    /**
     * publish <br/>
     * 广播消息 <br/>
     *
     * @param message
     * @return void
     * @author Mobile Web Group-lff
     * @date 2018年2月11日 上午10:12:27
     */
    public void send(String message) {
        try {
            //发送消息
            log.debug("业务{}--消息：" + message);
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            //输出log，继续下一个webSocket的msg发送
            log.error("发送消息失败：原因{}", e);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.session == null) ? 0 : this.session.getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WebSocket)) {
            return false;
        }
        WebSocket other = (WebSocket) obj;
        if (this.session == null) {
            if (other.session != null) {
                return false;
            }
        } else if (!this.session.getId().equals(other.session.getId())) {
            return false;
        }
        return true;
    }

    public String getServer() {
        return server;
    }

    public String getId() {
        return id;
    }

    public WebSocketHandler getWebSocketHandler() {
        return webSocketHandler;
    }

}
