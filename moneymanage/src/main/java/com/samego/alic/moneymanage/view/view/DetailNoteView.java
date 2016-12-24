package com.samego.alic.moneymanage.view.view;

/**
 * 便签详情视图
 * Created by alic on 16-9-21.
 */
public interface DetailNoteView {
    /**
     * 初始化UI
     */
    void initUI();

    /**
     * 初始化Icon
     */
    void initIcon();

    /**
     * 显示数据
     */
    void displayMessage();

    /**
     * 删除便签
     */
    void deleteNote();

    /**
     * 修改便签
     */
    void modifyNote();

    /**
     * 返回便签列表
     */
    void toNoteListFragment();

    /**
     * 获取noteId
     * @return id
     */
    int getId();

    /**
     * 提示Toast
     */
    void showTip();

    /**
     * 修改note
     */
    void toModifyActivity();
}
