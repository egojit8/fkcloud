package com.egojit.cloud.common.utils;

import java.util.UUID;

/**
 * xxxx
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public class GuidUtils {

    /**
     *
     * @return
     */
    public static String guid(){
        String strGuid= UUID.randomUUID().toString();
        return strGuid.replace("-","");
    }
}
