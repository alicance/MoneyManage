package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.IncomeModule;
import com.samego.alic.moneymanage.module.IncomeModuleImpl;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.HomeIncomeView;



/**
 * 收入主持
 * Created by alic on 16-9-21.
 */
public class HomeIncomePresenter {
    private IncomeModule incomeModule;
    private HomeIncomeView homeIncomeView;
    private Context context;
    private Handler handler;

    public HomeIncomePresenter(HomeIncomeView homeIncomeView, Context context) {
        this.homeIncomeView = homeIncomeView;
        this.context = context;
        handler = new Handler(Looper.getMainLooper());
        incomeModule = new IncomeModuleImpl();
    }

    public void toAddIncomeActivity(){
        homeIncomeView.toAddIncomeActivity();
    }

    public void display(){
        homeIncomeView.displayIncomeItems(incomeModule.getIncomes(context, DataUtil.readSharedPreference(context,"username",null)));
    }
}
