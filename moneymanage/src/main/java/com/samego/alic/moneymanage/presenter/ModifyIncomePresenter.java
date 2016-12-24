package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.IncomeModule;
import com.samego.alic.moneymanage.module.IncomeModuleImpl;
import com.samego.alic.moneymanage.view.view.ModifyIncomeView;


/**
 * 修改便签助手
 * Created by alic on 16-9-21.
 */
public class ModifyIncomePresenter {
    private IncomeModule incomeModule;
    private ModifyIncomeView modifyIncomeView;
    private Context context;
    private Handler handler;

    /**
     * construct
     * @param modifyIncomeView m
     * @param context c
     */
    public ModifyIncomePresenter(ModifyIncomeView modifyIncomeView, Context context) {
        this.modifyIncomeView = modifyIncomeView;
        this.context = context;
        incomeModule = new IncomeModuleImpl();
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 显示原始数据
     */
    public void display(){
        modifyIncomeView.display();
    }

    public void modifyIncome(){
        incomeModule.modifyIncome(context,modifyIncomeView.getAfterIncome());
        modifyIncomeView.showTip(0);
        modifyIncomeView.toIncomeListFragment();
    }
}
