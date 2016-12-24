package com.samego.alic.moneymanage.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.presenter.WelcomePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.view.view.WelcomeView;

/**
 *
 * Created by alic on 16-9-19.
 */
public class WelcomeActivity extends Activity implements WelcomeView {
    private WelcomePresenter welcomePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        init();
        welcomePresenter.toWhere();
    }

    void init() {
        welcomePresenter = new WelcomePresenter(this, this);
    }

    @Override
    public void toLogin() {
        Intent loginIntent = new Intent();
        loginIntent.setClass(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void toHome() {
        Intent mainIntent = new Intent();
        mainIntent.setClass(this, MainActivity.class);
        startActivity(mainIntent);
        ActivityCollector.addActivity(this);
        finish();
    }
}
