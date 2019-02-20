package com.egojit.cloud.images;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * docker配置
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
@Configuration
@ConfigurationProperties( prefix = "fkcloud.docker")
public class ImageConfig {

    /**
     * 镜像相关配置
     */
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public class Image {
        /**
         * 缓存地址
         */
        private String cacheLocation;

        public String getCacheLocation() {
            return cacheLocation;
        }

        public void setCacheLocation(String cacheLocation) {
            this.cacheLocation = cacheLocation;
        }
    }

}


