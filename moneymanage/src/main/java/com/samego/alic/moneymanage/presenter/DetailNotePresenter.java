package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.NoteModule;
import com.samego.alic.moneymanage.module.NoteModuleImpl;
import com.samego.alic.moneymanage.view.view.DetailNoteView;

/**
 *
 * Created by alic on 16-9-21.
 */
public class DetailNotePresenter {
    private NoteModule noteModule;
    private DetailNoteView detailNoteView;
    private Context context;
    private Handler handler;

    public DetailNotePresenter(Context context, DetailNoteView detailNoteView) {
        this.context = context;
        this.detailNoteView = detailNoteView;
        handler = new Handler(Looper.getMainLooper());
        noteModule = new NoteModuleImpl();
    }

    public void deleteNote(){
        noteModule.deleteNote(context, String.valueOf(detailNoteView.getId()));
        detailNoteView.showTip();
        detailNoteView.toNoteListFragment();
    }
    public void toModifyActivity(){
        detailNoteView.toModifyActivity();
    }
}
