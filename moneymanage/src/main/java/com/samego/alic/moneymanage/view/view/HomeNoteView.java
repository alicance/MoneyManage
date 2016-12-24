package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Note;

import java.util.List;

/**
 * 主页便签视图
 * Created by alic on 16-9-20.
 */
public interface HomeNoteView {
    /**
     * 跳转add便签页面
     */
    void toAddActivity();

    /**
     * 显示便签
     * @param notes notes
     */
    void displayNoted(List<Note> notes);
}
