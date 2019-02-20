package com.egojit.cloud.common.base;

import java.io.Serializable;

/**
 * 基础接口返回接口模型
 * @author 高露 QQ：408365330
 */
public class BaseResult implements Serializable {

    /**
     * 结果编号【默认对应REST_RESULT枚举】可以扩展
     */
    private int code;

    /**
     * 调试信息，方便调试
     */
    private String debugInfo;

    /**
     * 接口返回内容
     */
    private Object body;

    /**
     * 构造函数
     * @param body 接口结果
     * @param result 结果
     */
    public BaseResult(Object body,REST_RESULT result){
        this.body=body;
        this.code=result.getCode();
    }

    public BaseResult(REST_RESULT result){
        this.code=result.getCode();
        this.body=result.getValue();
    }

    public BaseResult(){
       this("成功！");
    }
    public BaseResult(Object body){
        this(body,REST_RESULT.SUCCESS);
    }
    public BaseResult(Throwable throwable){
        this.debugInfo=throwable.getMessage();
        this.body=REST_RESULT.ERROR.getValue();
        this.code=REST_RESULT.ERROR.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * 接口请求结果
     */
    public enum REST_RESULT {
        SUCCESS(200,"成功"),
        ERROR(500,"未知错误"),
        NO_AUTH(401,"无权限");
        private int code;
        private String value;

        private REST_RESULT(int code,String value){
            this.code=code;
            this.value=value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
