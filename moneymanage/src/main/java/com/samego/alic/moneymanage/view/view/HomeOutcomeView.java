package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Outcome;

import java.util.List;

/**
 *
 * Created by alic on 16-9-21.
 */
public interface HomeOutcomeView {

    /**
     * 跳转到增加支出
     */
    void toAddOutcomeActivity();

    /**
     * 显示所有outcome
     * @param list List<Outcome>
     */
    void displayOutcomeItems(List<Outcome> list);
}
