package com.egojit.cloud.common.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Configuration
public class DockerConfig {

    /**
     * docker api地址
     * unix:///var/run/docker.sock
     * tcp://127.0.0.1:2375
     */
    @Value("${fkcloud.docker.api.url:tcp://127.0.0.1:2375}")
    private String dockerUrl;

    @Value("${fkcloud.docker.api.timeout:1000000}")
    private int timeout;
    @Value("${fkcloud.docker.api.version:1.38}")
    private String version;

    @Value("${fkcloud.docker.api.connect.maxcount:50}")
    private int maxCount;

    @Value("${fkcloud.docker.api.registry.url:http://127.0.0.1:5000}")
    private String registryUrl;

    @Bean
    public DockerClient dockerClient(){
        DockerCmdExecFactory dockerCmdExecFactory = new JerseyDockerCmdExecFactory()
                .withReadTimeout(timeout)
                .withConnectTimeout(timeout)
                .withMaxTotalConnections(maxCount)
                .withMaxPerRouteConnections(10);


        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerUrl)
//                .withDockerTlsVerify(true)
//                .withDockerCertPath("/home/user/.docker/certs")
//                .withDockerConfig("/home/user/.docker")
                .withApiVersion(version)
                .withRegistryUrl(registryUrl)
//                .withRegistryUsername("dockeruser")
//                .withRegistryPassword("ilovedocker")
//                .withRegistryEmail("dockeruser@github.com")
                .build();

        DockerClient dockerClient = DockerClientBuilder.getInstance(config)
                .withDockerCmdExecFactory(dockerCmdExecFactory)
                .build();
        return dockerClient;
    }
}
