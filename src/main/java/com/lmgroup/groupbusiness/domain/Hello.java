package com.lmgroup.groupbusiness.domain;

import java.io.Serializable;

public class Hello implements Serializable {
    private static final long serialVersionUID = -3946734305303957850L;
    private int id;
    private String city;
    private Integer num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
