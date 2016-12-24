package com.samego.alic.moneymanage.module;

import android.content.Context;
import android.content.Intent;

import com.samego.alic.moneymanage.bean.Income;

import java.util.List;

/**
 * 收入Module
 * Created by alic on 16-9-21.
 */
public interface IncomeModule {
    /**
     * 增加收入
     * @param context context
     * @param income income
     */
    void addIncome(Context context,Income income);

    /**
     * 删除收入
     * @param context context
     * @param income income
     */
    void deleteIncome(Context context,Income income);

    /**
     * 修改收入
     * @param context context
     * @param income income
     */
    void modifyIncome(Context context,Income income);

    /**
     * 获取收入
     * @param context context
     * @param username income
     * @return incomes
     */
    List<Income> getIncomes(Context context,String username);
}
