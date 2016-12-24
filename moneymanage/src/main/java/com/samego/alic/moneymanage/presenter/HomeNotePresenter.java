package com.samego.alic.moneymanage.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.samego.alic.moneymanage.module.NoteModule;
import com.samego.alic.moneymanage.module.NoteModuleImpl;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.HomeNoteView;



/**
 *
 * Created by alic on 16-9-20.
 */
public class HomeNotePresenter {
    private HomeNoteView homeNoteView;
    private NoteModule noteModule;
    private Context context;
    private Handler handler;

    public HomeNotePresenter(Context context, HomeNoteView homeNoteView) {
        this.context = context;
        this.homeNoteView = homeNoteView;
        noteModule = new NoteModuleImpl();
        handler = new Handler(Looper.getMainLooper());
    }

    public void toAddNoteActivity(){
        homeNoteView.toAddActivity();
    }

    public void display(){
        homeNoteView.displayNoted(noteModule.getNoteList(context, DataUtil.readSharedPreference(context,"username",null)));
    }
}
