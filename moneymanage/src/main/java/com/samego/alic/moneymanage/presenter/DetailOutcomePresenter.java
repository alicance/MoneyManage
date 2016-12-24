package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.OutcomeModule;
import com.samego.alic.moneymanage.module.OutcomeModuleImpl;
import com.samego.alic.moneymanage.view.view.DetailOutcomeView;

/**
 *
 * Created by alic on 16-9-21.
 */
public class DetailOutcomePresenter {
    private OutcomeModule outcomeModule;
    private DetailOutcomeView detailOutcomeView;
    private Context context;
    private Handler handler;

    public DetailOutcomePresenter(Context context, DetailOutcomeView detailOutcomeView) {
        this.context = context;
        this.detailOutcomeView = detailOutcomeView;
        handler = new Handler(Looper.getMainLooper());
        outcomeModule = new OutcomeModuleImpl();
    }

    public void displayOutcome(){
        detailOutcomeView.displayOutcome();
    }

    public void deleteOutcome(){
        outcomeModule.deleteOutcome(context, detailOutcomeView.getOutcome());

    }
    public void toModifyActivity(){

    }
}
