package com.egojit.cloud.common.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * zk相关配置
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Configuration
public class ZookeeperConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(ZookeeperConfig.class);

    /**
     * zk 地址
     */
    @Value("${zookeeper.url:127.0.0.1:2181}")
    private String url;

    /**
     * 超时时间毫秒
     */
    @Value("${zookeeper.timeout:2000}")
    private int timeout;

    /**
     * 尝试间隔
     */
    @Value("${zookeeper.try.interval:1000}")
    private int tryInterval;
    /**
     * 尝试次数
     */
    @Value("${zookeeper.try.cout:3}")
    private int tryCount;

    /**
     * 尝试次数
     */
    @Value("${zookeeper.namespace:fkcloud}")
    private String namespace;


    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(tryInterval, tryCount);
        CuratorFramework clent = CuratorFrameworkFactory.builder()
                .connectString(url)
                .sessionTimeoutMs(timeout)
                .retryPolicy(retryPolicy)
                .namespace(namespace)
                .build();
        clent.start();//启动
        return clent;
    }

}
