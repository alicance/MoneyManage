package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.bean.Outcome;

/**
 *
 * Created by alic on 16-9-22.
 */
public interface ModifyOutcomeView {
    /**
     * 初始化UI
     */
    void initUI();

    /**
     * 获取Outcome
     */
    void getOutcome();

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
     * after outcome
     */
    Outcome getAfterOutcome();

    /**
     * 跳转
     */
    void toOutcomeListFragment();

    /**
     * tip
     * @param code int
     */
    void showTip(int code);
}
