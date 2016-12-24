package com.samego.alic.moneymanage.module;

import android.content.Context;

import com.samego.alic.moneymanage.bean.Outcome;

import java.util.List;

/**
 * 支出Module
 * Created by alic on 16-9-21.
 */
public interface OutcomeModule {
    /**
     * 增加支出
     * @param context context
     * @param outcome outcome
     */
    void addOutcome(Context context, Outcome outcome);

    /**
     * 删除支出
     * @param context context
     * @param outcome outcome
     */
    void deleteOutcome(Context context, Outcome outcome);

    /**
     * 修改支出
     * @param context context
     * @param outcome outcome
     */
    void modifyOutcome(Context context, Outcome outcome);

    /**
     * 获取支出
     * @param context context
     * @param username income
     * @return incomes
     */
    List<Outcome> getOutcome(Context context, String username);
}
