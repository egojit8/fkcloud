package com.egojit.cloud.service.Impl;

import com.egojit.cloud.common.base.BaseResult;
import com.egojit.cloud.common.base.BaseServiceImpl;
import com.egojit.cloud.mapper.AppMapper;
import com.egojit.cloud.model.dao.App;
import com.egojit.cloud.model.dao.RuningApp;
import com.egojit.cloud.service.AppService;
import com.egojit.cloud.service.RuningAppService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.InspectImageResponse;
import com.github.dockerjava.api.exception.ConflictException;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.security.RunAs;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Service
public class AppServiceImpl extends BaseServiceImpl<AppMapper, App> implements AppService {


    @Autowired
    DockerClient dockerClient;

    @Autowired
    RuningAppService runingAppService;

    /**
     * 容器前缀
     */
    private static String CONTAINER_PRX = "fkcloud-";


    /**
     * 安装pp
     *
     * @param app app信息
     * @return BaseResult
     */
    @Override
    public BaseResult install(App app, boolean startNow) {
        BaseResult result = new BaseResult("安装中，请稍后");
        //镜像本地存在直接创建
        if (isImagesExit(app.getName(), app.getVersion())) {
            RuningApp runingApp = createContarinerAndInsertDb(app);
            if (startNow) {
                start(runingApp.getContainerId());
            }
        } else {
            dockerClient.pullImageCmd(app.getName()).withTag(app.getVersion()).exec(new ResultCallback<PullResponseItem>() {
                @Override
                public void onStart(Closeable closeable) {
                    LOGGER.info("=======================开始拉取镜像===========================");
                }

                @Override
                public void onNext(PullResponseItem object) {
                    LOGGER.info("拉取中{}", object);
                }

                @Override
                public void onError(Throwable throwable) {
                    LOGGER.error("拉取出错{}", throwable);
                }

                @Override
                public void onComplete() {
                    LOGGER.info("=======================拉取结束===========================");
                    RuningApp runingApp = createContarinerAndInsertDb(app);
                    if (startNow) {
                        start(runingApp.getContainerId());
                    }

                }

                @Override
                public void close() throws IOException {
                    LOGGER.error("拉取关闭");
                }
            });
        }
        return result;
    }

    /**
     * 启动容器
     *
     * @param containerId runingapp 表中的id
     * @return
     */
    @Override
    public BaseResult start(String containerId) {
        try {
            dockerClient.startContainerCmd(containerId).exec();
            LOGGER.info("启动容器{}", containerId);
        } catch (NotFoundException ex) {
            LOGGER.error("容器{}不存在", containerId);
        }
        return updateStatu(containerId, 1) ? new BaseResult("启动成功,请稍后") : new BaseResult(new Throwable("运行失败"));
    }

    /**
     * 停止容器
     *
     * @param containerId runingapp 表中的id
     * @return
     */
    @Override
    public BaseResult stop(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
        return updateStatu(containerId, 0) ? new BaseResult("停止成功") : new BaseResult(new Throwable("停止"));
    }

    /**
     * 重启
     *
     * @param containerId 容器id
     * @return
     */
    @Override
    public BaseResult restart(String containerId) {
        dockerClient.restartContainerCmd(containerId).exec();
        return updateStatu(containerId, 1) ? new BaseResult("重启成功，请稍后") : new BaseResult(new Throwable("重启失败，稍后重试"));
    }

    /**
     * 关闭容器端口
     *
     * @param containerId
     * @return
     */
    @Override
    public BaseResult closeOrOpenPort(String containerId, boolean open) {
        InspectContainerResponse container = dockerClient.inspectContainerCmd(containerId).exec();
        String id = dockerClient.commitCmd(containerId).withRepository(container.getName()).withTag("changeport").exec();
        if (container.getState().getRunning()) {
            dockerClient.stopContainerCmd(containerId).exec();
        }
        dockerClient.removeContainerCmd(containerId).exec();
        RuningApp runingApp=new RuningApp();
        runingApp.setTitle(container.getName().replace(CONTAINER_PRX,""));
        runingApp.setVersion("changeport");
        if(open) {
            runingApp.setInsidePort(container.getConfig().getExposedPorts()[0].getPort());
            runingApp.setOutsidePort(container.getConfig().getExposedPorts()[0].getPort());
        }
        String cId= createContainer(runingApp);
        RuningApp runingUpdate=new RuningApp();
        runingUpdate.setId(cId);
        runingUpdate.setContainerId(cId);
        runingUpdate.setImageName(container.getName());
        Example upExemple=new Example(RuningApp.class);
        upExemple.createCriteria().andEqualTo("containerId",container.getId());
        int count= runingAppService.updateByExampleSelective(runingUpdate,upExemple);
        LOGGER.info("修改数量：{}",count);
        return new BaseResult("关闭端口成功");
    }


    /**
     * 更新表状态
     *
     * @param containerId
     * @param statu
     * @return
     */
    private boolean updateStatu(String containerId, int statu) {
        RuningApp runingApp = new RuningApp();
        runingApp.setStatus(statu);
        runingApp.setId(containerId);
        int count = runingAppService.updateByPrimaryKeySelective(runingApp);
        if (count > 0) {
            LOGGER.error("修改数据库app状态为{}成功！启动容器成功", statu);
            return true;
        } else {
            LOGGER.error("修改数据库app状态为{}失败！", statu);
            return false;
        }
    }

    /**
     * 插入数据库
     *
     * @param app
     */
    private RuningApp createContarinerAndInsertDb(App app) {

        RuningApp runingApp = new RuningApp();
        Example example = new Example(RuningApp.class);
        example.createCriteria().andEqualTo("appId", app.getId());
        int count = runingAppService.selectCountByExample(example);
        runingApp.setAppId(app.getId());
        runingApp.setInsidePort(app.getDefaultPort());
        runingApp.setOutsidePort(getPort(app.getDefaultPort()));
        runingApp.setTitle(app.getName() + "-" + count);
        runingApp.setImageName(app.getName());
        runingApp.setVersion(app.getVersion());
        runingApp.setGroupId("1");
        String id = createContainer(runingApp);
        runingApp.setContainerId(id);
        runingApp.setId(id);
        runingAppService.insert(runingApp);
        return runingApp;
    }

    private String createContainer(RuningApp app) {
        LOGGER.debug("创建镜像");
        String image = String.format("%s:%s", app.getImageName(), app.getVersion());
        CreateContainerCmd createContainerCmd = dockerClient.createContainerCmd(image)
                .withName(CONTAINER_PRX + app.getTitle())
                .withAliases(CONTAINER_PRX + app.getTitle());
        if (app.getInsidePort() != null && app.getInsidePort() > 0) {
            ExposedPort tcpPort = ExposedPort.tcp(app.getInsidePort());
            createContainerCmd.withExposedPorts(tcpPort);
            //设置端口映射
            if (app.getOutsidePort() != null && app.getOutsidePort() > 0) {
                Ports portBindings = new Ports();
                portBindings.bind(tcpPort, Ports.Binding.bindPort(app.getOutsidePort()));
                LOGGER.debug("端口信息：{}", portBindings);
                createContainerCmd.withPortBindings(portBindings);
            }
        }
        try {
            LOGGER.info("创建容器：{}", app.getTitle());
            return createContainerCmd.exec().getId();
        } catch (ConflictException ex) {
            LOGGER.warn("创建容器{}失败，重新创建", app.getTitle());
            dockerClient.removeContainerCmd(CONTAINER_PRX + app.getTitle()).exec();
            return createContainerCmd.exec().getId();
        }

    }

    /**
     * 判断镜像是否本地存在
     *
     * @param imageName
     * @return
     */
    private boolean isImagesExit(String imageName, String version) {
        List<Image> list = dockerClient.listImagesCmd().withImageNameFilter(imageName + ":" + version).exec();
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 测试本机端口是否被使用
     *
     * @param port
     * @return
     */
    public static boolean isLocalPortUsing(int port) {
        boolean flag = true;
        try {
            //如果该端口还在使用则返回true,否则返回false,127.0.0.1代表本机
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }

    /***
     * 测试主机Host的port端口是否被使用
     * @param host
     * @param port
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress Address = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(Address, port);  //建立一个Socket连接
            flag = true;
        } catch (IOException e) {

        }
        return flag;
    }

    /**
     * 获取没有使用的端口，如果默认端口没有使用直接使用默认端口，否则1024-65535之前选一个没使用的端口
     *
     * @param defaultPort
     * @return
     */
    private Integer getPort(Integer defaultPort) {
        Random random = new Random();
        while (isLocalPortUsing(defaultPort)) {
            defaultPort = random.nextInt(65535) + 1024;
        }
        return defaultPort;
    }


}
