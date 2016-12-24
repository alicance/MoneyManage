package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.bean.Outcome;

/**
 *
 * Created by alic on 16-9-22.
 */
public interface DetailOutcomeView {
    /**
     * 初始化UI
     */
    void initUI();

    /**
     * 获取outcome
     */
    Outcome getOutcome();

    /**
     * 显示收入详情
     */
    void displayOutcome();

    /**
     * 删除income
     */
    void deleteOutcome();

    /**
     * 跳转
     */
    void toOutcomeListFragment();

    /**
     * 提示
     * @param code code
     */
    void showTip(int code);

    /**
     * 跳转
     */
    void toModifyOutcomeActivity();
}
