package com.samego.alic.moneymanage.view.view;

import com.samego.alic.moneymanage.bean.Note;

/**
 * 添加便签视图
 * Created by alic on 16-9-21.
 */
public interface AddNoteView {

    /**
     * 获取便签标题
     * @return title
     */
    String getNoteTitle();

    /**
     * 获取便签内容
     * @return content
     */
    String getNoteContent();

    /**
     * 获取便签
     * @return note
     */
    Note getNote();

    /**
     * tip提示
     * @param code code
     */
    void showTip(int code);

    /**
     * 返回上一级
     */
    void toBack();

    /**
     * 便签list菜单
     */
    void toHomeNoteFragment();
}
