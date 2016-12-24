package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.UserModule;
import com.samego.alic.moneymanage.module.UserModuleImpl;
import com.samego.alic.moneymanage.module.listener.OnLoginListener;
import com.samego.alic.moneymanage.view.view.LoginView;



/**
 *
 * Created by alic on 16-9-14.
 */
public class LoginPresenter {
    private LoginView loginView;
    private UserModule userModule;
    private Context context;
    private Handler handler;

    public LoginPresenter(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
        userModule = new UserModuleImpl();
        handler = new Handler(Looper.getMainLooper());
    }

    public void login(){
        //显示登陆loading
        handler.post(new Runnable() {
            @Override
            public void run() {
                loginView.showLoading();
            }
        });
        userModule.login(context,loginView.getUsername(), loginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess() {
                loginView.saveUserMessage();
                loginView.toMainActivity();
            }

            @Override
            public void loginFailed() {
                loginView.setFailedCode(2);
                loginView.showFailed();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.hideLoading();
                    }
                });
            }
        });
    }
    public void defaultUser(){
        userModule.defaultUser(context);
    }
}
