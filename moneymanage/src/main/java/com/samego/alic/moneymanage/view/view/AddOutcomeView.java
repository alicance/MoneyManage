package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.bean.Outcome;

/**
 *
 * Created by alic on 16-9-21.
 */
public interface AddOutcomeView {
    /**
     * 支出Outcome
     * @return Income
     */
    Outcome getOutcome();

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
     * 支出menuItem
     */
    void toHomeOutcomeItem();
}
