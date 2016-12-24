package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Note;

/**
 * 修改便签视图
 * Created by alic on 16-9-21.
 */
public interface ModifyNoteView {

    /**
     * 初始化UI
     */
    void initUI();
    /**
     * 获取传过来的note
     */
    void getNote();

    /**
     *
     */
    void displayMessage();

    /**
     * 获取修改后的Note
     * @return n
     */
    Note getAfterNote();

    /**
     * TIP
     */
    void showTip(int code);

    /**
     * to
     */
    void toNoteListFragment();
}
