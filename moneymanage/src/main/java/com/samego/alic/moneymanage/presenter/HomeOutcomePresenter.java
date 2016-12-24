package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.OutcomeModule;
import com.samego.alic.moneymanage.module.OutcomeModuleImpl;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.HomeOutcomeView;


/**
 * 收入主持
 * Created by alic on 16-9-21.
 */
public class HomeOutcomePresenter {
    private OutcomeModule outcomeModule;
    private HomeOutcomeView homeOutcomeView;
    private Context context;
    private Handler handler;

    public HomeOutcomePresenter(HomeOutcomeView homeOutcomeView, Context context) {
        this.homeOutcomeView = homeOutcomeView;
        this.context = context;
        handler = new Handler(Looper.getMainLooper());
        outcomeModule = new OutcomeModuleImpl();
    }

    public void toAddOutcomeActivity(){
        homeOutcomeView.toAddOutcomeActivity();
    }

    public void display(){
        homeOutcomeView.displayOutcomeItems(outcomeModule.getOutcome(context, DataUtil.readSharedPreference(context, "username", null)));
    }
}
