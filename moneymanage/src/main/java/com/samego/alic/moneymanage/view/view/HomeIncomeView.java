package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Income;

import java.util.List;

/**
 * HomeIncomeView收入列表
 * Created by alic on 16-9-21.
 */
public interface HomeIncomeView {

    /**
     * 跳转到增加收入
     */
    void toAddIncomeActivity();

    /**
     * 显示所有income
     * @param list List<Income>
     */
    void displayIncomeItems(List<Income> list);
}
