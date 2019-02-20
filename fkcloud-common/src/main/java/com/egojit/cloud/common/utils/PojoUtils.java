package com.egojit.cloud.common.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;


/**
 * 对象操作
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public class PojoUtils {

    private static Logger LOGGER= LoggerFactory.getLogger(PojoUtils.class);

    /**
     * 对象拷贝
     * @param fromObj 原对象
     * @param toObj 目标对象
     */
    public static void copObject(Object fromObj,Object toObj){
        LOGGER.debug("把对象{}拷贝去对象{}",fromObj,toObj);
        BeanUtils.copyProperties(fromObj,toObj);
    }
}
