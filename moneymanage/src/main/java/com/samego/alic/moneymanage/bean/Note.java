package com.samego.alic.moneymanage.bean;

import java.io.Serializable;

/**
 * 便签Bean
 * Created by alic on 16-9-20.
 */
public class Note implements Serializable {
    private int id;             //id
    private String username;    //用户
    private String title;       //标题
    private String content;     //内容
    private String datetime;    //时间

    public Note(){

    }

    public Note(int id, String username, String title, String content, String datetime) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
