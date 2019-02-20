package com.egojit.cloud.common.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.StatsCmd;
import com.github.dockerjava.api.model.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.IOException;

/**
 * Stats websocket handler
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Service("statsWebSocketHandler")
public class StatsWebSocketHandler implements WebSocketHandler {
    private static Logger LOGGER=LoggerFactory.getLogger(StatsWebSocketHandler.class);
    private WebSocket mSocket;
    @Autowired
    DockerClient dockerClient;

    private ResultCallback callback;
    private boolean isStop=false;
    private String bizId;

    @Override
    public void init(WebSocket socket,String bizId) {
        this.mSocket=socket;
        this.bizId=bizId;
        // bizId=ContainerId
       callback= dockerClient.statsCmd(bizId).exec(new ResultCallback<Statistics>() {
            @Override
            public void onStart(Closeable closeable) {
                LOGGER.debug("=================start to get container running info================");
            }

            @Override
            public void onNext(Statistics object) {
                if(!isStop) {
                    String strStatis = JSON.toJSONString(object);
                    mSocket.send(strStatis);
                    LOGGER.debug("容器信息:{}", strStatis);
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
            }

            @Override
            public void close() throws IOException {
                LOGGER.info("关闭了:{}",bizId);
            }
        });
    }

    @Override
    public void destory() {
        try {
            isStop=true;
            callback.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        callback=null;
    }
}
