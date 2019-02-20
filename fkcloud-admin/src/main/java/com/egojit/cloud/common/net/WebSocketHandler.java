package com.egojit.cloud.common.net;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public interface WebSocketHandler {

    void init(WebSocket socket,String bizId);

    void destory();
}
