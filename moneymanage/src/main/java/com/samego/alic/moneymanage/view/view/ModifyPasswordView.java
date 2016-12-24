package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.User;

/**
 * 修改密码视图
 * Created by alic on 16-9-20.
 */
public interface ModifyPasswordView {
    /**
     * 获取用户
     * @return user
     */
    User getUser();

    /**
     * 获取新密码
     * @return np
     */
    String getNewPassword();

    /***
     * 显示tip
     * @param code code
     */
    void showTip(int code);

    /**
     * login
     */
    void toLogin();
}
