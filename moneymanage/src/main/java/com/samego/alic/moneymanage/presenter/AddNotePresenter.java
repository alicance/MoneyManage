package com.samego.alic.moneymanage.presenter;

import android.content.Context;

import com.samego.alic.moneymanage.module.NoteModule;
import com.samego.alic.moneymanage.module.NoteModuleImpl;
import com.samego.alic.moneymanage.module.listener.OnAddNoteListener;
import com.samego.alic.moneymanage.utils.AppUtil;
import com.samego.alic.moneymanage.view.view.AddNoteView;

/**
 *
 * Created by alic on 16-9-21.
 */
public class AddNotePresenter {
    private AddNoteView addNoteView;
    private Context context;
    private NoteModule noteModule;

    public AddNotePresenter(AddNoteView addNoteView, Context context) {
        this.addNoteView = addNoteView;
        this.context = context;
        noteModule = new NoteModuleImpl();
    }

    public void addNote(){
        noteModule.addNote(context, addNoteView.getNote(), new OnAddNoteListener() {
            @Override
            public void addSuccess() {
                AppUtil.LogMessage("插入了一条便签~~~");
                addNoteView.showTip(0);
                addNoteView.toHomeNoteFragment();
            }

            @Override
            public void addFailed() {
                addNoteView.showTip(2);
            }
        });
    }

    public void toBack(){
        addNoteView.toBack();
    }
}
