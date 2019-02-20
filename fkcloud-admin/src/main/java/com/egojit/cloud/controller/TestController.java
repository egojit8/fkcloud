package com.egojit.cloud.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.api.model.Swarm;
import com.github.dockerjava.api.model.SwarmJoinTokens;
import com.github.dockerjava.api.model.SwarmSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    DockerClient dockerClient;

    @GetMapping("/test")
    public Object test(){
        SwarmSpec swarmSpec=new SwarmSpec();
        try {
            dockerClient.initializeSwarmCmd(swarmSpec).exec();
        }catch (DockerException ex){
            ex.printStackTrace();
        }
        Swarm swarm= dockerClient.inspectSwarmCmd().exec();


        SwarmJoinTokens tokens= swarm.getJoinTokens();
        return tokens;
    }
}
