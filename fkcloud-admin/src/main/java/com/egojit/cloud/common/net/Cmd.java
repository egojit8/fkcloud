package com.egojit.cloud.common.net;

/**
 * socket命令
 *
 * @author 高露 QQ：408365330
 * @date $date$
 */
public enum  Cmd {
    START("start", 1),STAIS("stats" ,2);

    private String value;
    private int key;

    private Cmd(String value, int key) {
        this.value = value;
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
