package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Income;

/**
 *
 * Created by alic on 16-9-21.
 */
public interface AddIncomeView {
    /**
     * 收入Income
     * @return Income
     */
    Income getIncome();

    /**
     * 验证输入框
     * @return bool
     */
    boolean checkInput();

    /**
     * tip
     */
    void showTip(int code);

    /**
     * 收入menuItem
     */
    void toHomeIncomeItem();
}
