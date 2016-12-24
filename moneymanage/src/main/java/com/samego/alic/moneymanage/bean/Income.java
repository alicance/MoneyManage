package com.samego.alic.moneymanage.bean;

import java.io.Serializable;

/**
 * 收入bean
 * Created by alic on 16-9-19.
 */
public class Income implements Serializable{
    private int id;
    private String title;       //类型
    private String datetime;    //时间
    private String money;       //金额
    private String payer;       //付款方
    private String description; //备注
    private String username;    //用户

    public Income() {
    }

    public Income(int id, String title, String datetime, String money, String payer, String description, String username) {

        this.id = id;
        this.title = title;
        this.datetime = datetime;
        this.money = money;
        this.payer = payer;
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

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
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
