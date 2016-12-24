package com.samego.alic.moneymanage.view.view;

/**
 *
 * Created by alic on 16-9-13.
 */
public interface LoginView {
    /**
     * 获取用户名
     * @return String username
     */
    String getUsername();

    /**
     * 获取密码 password
     * @return String
     */
    String getPassword();

    /**
     * 保存用户信息
     */
    void saveUserMessage();

    /**
     * 读取用户信息
     */
    void readUserMessage();

    /**
     * 显示头像
     */
    void displayFace();

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 跳转主界面
     */
    void toMainActivity();

    /**
     * 显示错误
     */
    void showFailed();

    /**
     * 设置错误代码
     * @param code int
     */
    void setFailedCode(int code);
}
