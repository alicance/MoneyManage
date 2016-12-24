package com.samego.alic.moneymanage.bean;

import java.io.Serializable;

/**
 *
 * Created by alic on 16-9-19.
 */

public class Outcome implements Serializable {
    private int id;
    private String title;       //类型
    private String datetime;    //时间
    private String money;       //金额
    private String place;       //地点
    private String description; //备注
    private String username;    //用户


    public Outcome() {

    }

    public Outcome(int id, String title, String datetime, String money, String place, String description,String username) {
        this.id = id;
        this.title = title;
        this.datetime = datetime;
        this.money = money;
        this.place = place;
        this.description = description;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
