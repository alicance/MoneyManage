package com.samego.alic.moneymanage.common;

/**
 *
 * Created by alic on 16-9-19.
 */
public final class AppConfig {
    /**
     * sqlite文件
     */
    public final static String DATABASE_NAME = "moneyManager";
    /**
     * 收入表
     */
    public final static String TABLE_INCOME_NAME = "income";
    /**
     * 支出表
     */
    public final static String TABLE_OUTCOME_NAME = "outcome";
    /**
     * 本地数据库的用户表
     */
    public final static String TABLE_USER_NAME = "user";
    /**
     * 便签表
     */
    public final static String TABLE_NOTE_NAME = "note";
    /**
     * 调试模式
     */
    public final static boolean DEBUG = true;
    /**
     * SharedPreferences中的user文件
     */
    public final static String SHAREPREFERENCES_NAME = "user";
    /**
     * 欢迎页面的时间-毫秒
     */
    public final static int WELCOME_DELAYED = 1000;

    /**
     * 默认的用户名
     */
    public final static String DEFAULT_USERNAME = "alic";

    /**
     * 默认的密码
     */
    public final static String DEFAULT_PASSWORD = "alic";
}
