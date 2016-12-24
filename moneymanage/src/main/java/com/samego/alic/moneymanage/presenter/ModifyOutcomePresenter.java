package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.OutcomeModule;
import com.samego.alic.moneymanage.module.OutcomeModuleImpl;
import com.samego.alic.moneymanage.view.view.ModifyOutcomeView;


/**
 * 修改便签助手
 * Created by alic on 16-9-21.
 */
public class ModifyOutcomePresenter {
    private OutcomeModule outcomeModule;
    private ModifyOutcomeView modifyOutcomeView;
    private Context context;
    private Handler handler;

    /**
     * construct
     * @param modifyOutcomeView m
     * @param context c
     */
    public ModifyOutcomePresenter(ModifyOutcomeView modifyOutcomeView, Context context) {
        this.modifyOutcomeView = modifyOutcomeView;
        this.context = context;
        outcomeModule = new OutcomeModuleImpl();
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 显示原始数据
     */
    public void display(){
        modifyOutcomeView.display();
    }

    public void modifyOutcome(){
        outcomeModule.modifyOutcome(context,modifyOutcomeView.getAfterOutcome());
        modifyOutcomeView.showTip(0);
        modifyOutcomeView.toOutcomeListFragment();
    }
}
