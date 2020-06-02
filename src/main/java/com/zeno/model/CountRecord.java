package com.zeno.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @program: zeno-hope-zj
 * @description:
 * @author: Mr.Zeno
 * @create: 2020-05-25 09:33
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountRecord {

    private String typeName;

    private String ip;

    private String curTime;

    private Integer oprCount;

    private Integer type;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public Integer getOprCount() {
        return oprCount;
    }

    public void setOprCount(Integer oprCount) {
        this.oprCount = oprCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CountRecord{" +
                "typeName='" + typeName + '\'' +
                ", ip='" + ip + '\'' +
                ", curTime='" + curTime + '\'' +
                ", oprCount=" + oprCount +
                ", type=" + type +
                '}';
    }
}
