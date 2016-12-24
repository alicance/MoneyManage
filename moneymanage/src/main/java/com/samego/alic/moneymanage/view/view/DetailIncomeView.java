package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Income;

/**
 * 收入详情
 * Created by alic on 16-9-22.
 */
public interface DetailIncomeView {
    /**
     * 初始化UI
     */
    void initUI();

    /**
     * 获取income
     */
    Income getIncome();

    /**
     * 显示收入详情
     */
    void displayIncome();

    /**
     * 删除income
     */
    void deleteIncome();

    /**
     * 跳转
     */
    void toIncomeListFragment();

    /**
     * 提示
     * @param code code
     */
    void showTip(int code);

    /**
     * 跳转
     */
    void toModifyIncomeActivity();
}
