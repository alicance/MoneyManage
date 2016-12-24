package com.samego.alic.moneymanage.presenter;

import android.content.Context;

import com.samego.alic.moneymanage.view.view.HomeSystemView;

/**
 * 系统菜单主持
 * Created by alic on 16-9-20.
 */
public class HomeSystemPresenter {
    private HomeSystemView homeSystemView;
    private Context context;

    public HomeSystemPresenter(Context context, HomeSystemView homeSystemView) {
        this.context = context;
        this.homeSystemView = homeSystemView;
    }

    public void modifyPassword(){
        homeSystemView.modifyPassword();
    }
    public void logout(){
        homeSystemView.logout();
    }
    public void changeUser(){
        homeSystemView.changeUser();
    }
}
