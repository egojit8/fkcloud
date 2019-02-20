package com.egojit.cloud.common.net;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Configuration
public class WebSocketConfig {



    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

//    @Bean("taskExecutor")
//    public Executor taskExecutor(){
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);//线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
//        taskExecutor.setAwaitTerminationSeconds(60);//设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
//        taskExecutor.setCorePoolSize(4);
//        taskExecutor.setMaxPoolSize(8);
//        taskExecutor.setQueueCapacity(Integer.MAX_VALUE);//缓冲执行任务的队列
//        taskExecutor.setKeepAliveSeconds(60);//当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
//        taskExecutor.setThreadNamePrefix("kanjia-websocket-thread-");
//        return taskExecutor;
//    }
}
