package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.common.AppConfig;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.WelcomeView;

/**
 *
 * Created by alic on 16-9-19.
 */
public class WelcomePresenter {
    private WelcomeView welcomeView;
    private Context context;
    private Handler handler;

    public WelcomePresenter(WelcomeView welcomeView, Context context) {
        this.welcomeView = welcomeView;
        this.context = context;
        handler = new Handler(Looper.getMainLooper());
    }

    public void toWhere(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(DataUtil.readSharedPreference(context,"username",null) != null){
                    DataUtil.saveSharedPreference(context,"menuItem","income");
                    welcomeView.toHome();
                }else {
                    DataUtil.saveSharedPreference(context,"menuItem","income");
                    welcomeView.toLogin();
                }
            }
        }, AppConfig.WELCOME_DELAYED);
    }
}
