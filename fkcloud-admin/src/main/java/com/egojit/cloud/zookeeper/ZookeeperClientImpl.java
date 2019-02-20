package com.egojit.cloud.zookeeper;

import com.egojit.cloud.service.Impl.UserServiceImpl;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * zk 获取数据的封装
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Service
public class ZookeeperClientImpl implements ZookeeperClinet {
    private static Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    CuratorFramework curatorFramework;


    /**
     * 获取数据
     * @param path
     * @return
     */
    @Override
    public String getData(String path) {
        String strData=null;
        try {
            byte[] data= curatorFramework.getData().forPath(path);
            strData=new String(data);
            LOGGER.debug("getData from zk:{}",strData);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("getData for {} error:{}",path,e);
        }
        return strData;
    }
}
