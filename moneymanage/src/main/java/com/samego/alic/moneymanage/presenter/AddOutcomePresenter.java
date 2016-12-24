package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.OutcomeModule;
import com.samego.alic.moneymanage.module.OutcomeModuleImpl;
import com.samego.alic.moneymanage.view.view.AddOutcomeView;

/**
 * 添加收入主持
 * Created by alic on 16-9-22.
 */
public class AddOutcomePresenter {
    private AddOutcomeView addOutcomeView;
    private OutcomeModule outcomeModule;
    private Context context;
    private Handler handler;

    public AddOutcomePresenter(AddOutcomeView addOutcomeView, Context context) {
        this.addOutcomeView = addOutcomeView;
        this.context = context;
        outcomeModule = new OutcomeModuleImpl();
        handler = new Handler(Looper.getMainLooper());
    }

    public void addOutcome(){
        outcomeModule.addOutcome(context, addOutcomeView.getOutcome());
        addOutcomeView.showTip(0);
    }
    public void toHomeOutcomeItem(){
        addOutcomeView.toHomeOutcomeItem();
    }
}
