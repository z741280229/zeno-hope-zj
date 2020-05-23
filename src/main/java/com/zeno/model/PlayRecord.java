package com.zeno.model;


/**
 * @program: zeno-hope-zj
 * @description:
 * @author: Mr.Zeno
 * @create: 2020-05-22 12:09
 **/
public class PlayRecord {

    private String ip;

    private String time;

    public PlayRecord() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PlayRecord{" +
                "ip='" + ip + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
