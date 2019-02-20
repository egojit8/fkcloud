package com.egojit.cloud.images;

import com.egojit.cloud.model.dao.App;

/**
 * 镜像服务
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public interface ImageService {

    void saveImage(String imageName,String tag);

    /**
     * 判断本地是否存在镜像
     * @param imageName
     * @param tag
     * @return
     */
    boolean isLocalExist(String imageName,String tag);

}
