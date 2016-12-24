package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.IncomeModule;
import com.samego.alic.moneymanage.module.IncomeModuleImpl;
import com.samego.alic.moneymanage.view.view.AddIncomeView;

/**
 * 添加收入主持
 * Created by alic on 16-9-22.
 */
public class AddIncomePresenter {
    private AddIncomeView addIncomeView;
    private IncomeModule incomeModule;
    private Context context;
    private Handler handler;

    public AddIncomePresenter(AddIncomeView addIncomeView, Context context) {
        this.addIncomeView = addIncomeView;
        this.context = context;
        incomeModule = new IncomeModuleImpl();
        handler = new Handler(Looper.getMainLooper());
    }

    public void addIncome(){
        incomeModule.addIncome(context,addIncomeView.getIncome());
        addIncomeView.showTip(0);
    }
    public void toHomeIncomeItem(){
        addIncomeView.toHomeIncomeItem();
    }
}
