package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.IncomeModule;
import com.samego.alic.moneymanage.module.IncomeModuleImpl;
import com.samego.alic.moneymanage.view.view.DetailIncomeView;

/**
 *
 * Created by alic on 16-9-21.
 */
public class DetailIncomePresenter {
    private IncomeModule incomeModule;
    private DetailIncomeView detailNoteView;
    private Context context;
    private Handler handler;

    public DetailIncomePresenter(Context context, DetailIncomeView detailNoteView) {
        this.context = context;
        this.detailNoteView = detailNoteView;
        handler = new Handler(Looper.getMainLooper());
        incomeModule = new IncomeModuleImpl();
    }

    public void displayIncome(){
        detailNoteView.displayIncome();
    }

    public void deleteNote(){
        incomeModule.deleteIncome(context,detailNoteView.getIncome());

    }
    public void toModifyActivity(){

    }
}
