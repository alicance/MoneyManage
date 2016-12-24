package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Income;

/**
 *
 * Created by alic on 16-9-22.
 */
public interface ModifyIncomeView {
    /**
     * 初始化UI
     */
    void initUI();

    /**
     * 获取Income
     */
    void getIncome();

    /**
     * 显示原始数据
     */
    void display();

    /**
     * 监测input
     * @return bool
     */
    boolean checkInput();

    /**
     * after income
     */
    Income getAfterIncome();

    /**
     * 跳转
     */
    void toIncomeListFragment();

    /**
     * tip
     * @param code int
     */
    void showTip(int code);
}
