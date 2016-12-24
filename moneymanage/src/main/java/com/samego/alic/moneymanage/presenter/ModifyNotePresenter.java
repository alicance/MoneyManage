package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.NoteModule;
import com.samego.alic.moneymanage.module.NoteModuleImpl;
import com.samego.alic.moneymanage.view.view.ModifyNoteView;



/**
 * 修改便签助手
 * Created by alic on 16-9-21.
 */
public class ModifyNotePresenter {
    private NoteModule noteModule;
    private ModifyNoteView modifyNoteView;
    private Context context;
    private Handler handler;

    /**
     * construct
     * @param modifyNoteView m
     * @param context c
     */
    public ModifyNotePresenter(ModifyNoteView modifyNoteView, Context context) {
        this.modifyNoteView = modifyNoteView;
        this.context = context;
        noteModule = new NoteModuleImpl();
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 显示原始数据
     */
    public void display(){
        modifyNoteView.getNote();
        modifyNoteView.displayMessage();
    }

    public void modifyNote(){
        noteModule.modifyNote(context,modifyNoteView.getAfterNote());
        modifyNoteView.showTip(0);
        modifyNoteView.toNoteListFragment();
    }
}
