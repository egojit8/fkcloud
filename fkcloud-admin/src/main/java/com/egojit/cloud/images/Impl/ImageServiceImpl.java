package com.egojit.cloud.images.Impl;

import com.egojit.cloud.images.ImageConfig;
import com.egojit.cloud.images.ImageService;
import com.github.dockerjava.api.DockerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 镜像管理
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Service
public class ImageServiceImpl implements ImageService {
    private static Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    ImageConfig imageConfig;

    @Autowired
    DockerClient dockerClient;

    /**
     * 保存镜像压缩包到本地目录
     * @param imageName 镜像名称
     * @param tag 版本tag
     */
    @Override
    public void saveImage(String imageName, String tag) {
        InputStream inputStream = dockerClient.saveImageCmd(imageName)
                .withName(imageName)
                .withTag(tag)
                .exec();
        FileOutputStream cacheFileOut = null;
        try {
            File dir = new File(imageConfig.getImage().getCacheLocation());
            if (!dir.exists()) {
                LOGGER.debug("save images {} is not exist ;mkdir", dir.getAbsolutePath());
                dir.mkdir();
                LOGGER.debug("mkdir {} is ok", dir.getAbsolutePath());
            }
            int index;
            byte[] bytes = new byte[1024];
            File file = new File(imageConfig.getImage().getCacheLocation() + File.separator + imageName + ":" + tag + ".tar");
            LOGGER.debug("save images to: {}", file.getAbsolutePath());
            cacheFileOut = new FileOutputStream(file);
            while ((index = inputStream.read(bytes)) != -1) {
                cacheFileOut.write(bytes, 0, index);
                cacheFileOut.flush();
            }
            LOGGER.debug("save images to {} is ok", file.getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error("save images error: {}", ex);
        } finally {
            try {
                inputStream.close();
                cacheFileOut.close();
                LOGGER.error("close all stream ,is ok");
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("save images close stream error: {}", e);
            }

        }
    }

    @Override
    public boolean isLocalExist(String imageName, String tag) {
        return false;
    }





}
