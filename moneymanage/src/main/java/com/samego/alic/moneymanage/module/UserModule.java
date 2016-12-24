package com.samego.alic.moneymanage.module;

import android.content.Context;

import com.samego.alic.moneymanage.bean.User;
import com.samego.alic.moneymanage.module.listener.OnLoginListener;
import com.samego.alic.moneymanage.module.listener.OnModifyPSDListener;

/**
 * UserModule模块
 * Created by alic on 16-9-14.
 */
public interface UserModule {
    /**
     * 用户登陆
     * @param username 用户名
     * @param password 密码
     * @param onLoginListener 登陆结果监听是否登陆成功
     */
    void login(Context context,String username,String password,OnLoginListener onLoginListener);

    /**
     * 修改密码
     * @param context context
     */
    void modifyPassword(Context context,User user,String newPwd,OnModifyPSDListener onModifyPSDListener);

    /**
     * 本地生成一个账号 便于测试
     * @param context context
     */
    void defaultUser(Context context);

}
