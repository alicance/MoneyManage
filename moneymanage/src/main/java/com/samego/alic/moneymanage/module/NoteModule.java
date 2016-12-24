package com.samego.alic.moneymanage.module;

import android.content.Context;

import com.samego.alic.moneymanage.bean.Note;
import com.samego.alic.moneymanage.module.listener.OnAddNoteListener;

import java.util.List;

/**
 *
 * Created by alic on 16-9-21.
 */
public interface NoteModule {
    /**
     * 添加便签
     * @param context context
     * @param note note
     * @param onAddNoteListener listener
     */
    void addNote(Context context,Note note,OnAddNoteListener onAddNoteListener);

    /**
     * 获取所有便签记录
     * @param context context
     * @param username 用户名
     */
    List<Note> getNoteList(Context context,String username);

    /**
     * 删除便签
     * @param context context
     * @param id note id
     */
    void deleteNote(Context context,String id);

    /**
     * 修改便签
     * @param context context
     * @param note note
     */
    void modifyNote(Context context,Note note);
}
