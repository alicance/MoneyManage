package com.samego.alic.moneymanage.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.samego.alic.moneymanage.bean.Note;
import com.samego.alic.moneymanage.common.AppConfig;
import com.samego.alic.moneymanage.module.listener.OnAddNoteListener;
import com.samego.alic.moneymanage.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by alic on 16-9-21.
 */
public class NoteModuleImpl implements NoteModule {
    @Override
    public void addNote(Context context,Note note, OnAddNoteListener onAddNoteListener) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
        //生成ContentValues对象，key:列名  value:想插入的值
        ContentValues values = new ContentValues();
        values.put("username", note.getUsername());
        values.put("title", note.getTitle());
        values.put("content", note.getContent());
        values.put("datetime", note.getDatetime());
        //参数1：表名
        //参数2：如果你想插入空值，那么你必须指定它的所在的列
        //参数3：ContentValues对象
        long insert = writableDatabase.insert(AppConfig.TABLE_NOTE_NAME, null, values);
        if (insert>0)
            onAddNoteListener.addSuccess();
        else
            onAddNoteListener.addFailed();
        }

    @Override
    public List<Note> getNoteList(Context context, String username) {
        List<Note> notes = new ArrayList<>();

        //test
//        for (int index = 0; index<5 ; index++){
//            Note note = new Note();
//            note.setId(index);
//            note.setTitle("嗨~我是理财助手呢");
//            note.setContent("7777777");
//            note.setDatetime("2016-09-18");
//            notes.add(note);
//        }
        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context)
        );
        SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                AppConfig.TABLE_NOTE_NAME, null, "username=?", new String[]{username}, null, null, null, null
        );
        while (cursor.moveToNext()){
            Note note = new Note();
            note.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            note.setContent(cursor.getString(cursor.getColumnIndex("content")));
            note.setDatetime(cursor.getString(cursor.getColumnIndex("datetime")));
            note.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    @Override
    public void deleteNote(Context context, String id) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
        writableDatabase.delete(AppConfig.TABLE_NOTE_NAME, "id=?", new String[]{id});
    }

    @Override
    public void modifyNote(Context context, Note note) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title",note.getTitle());
        values.put("content",note.getContent());
        writableDatabase.update(AppConfig.TABLE_NOTE_NAME, values, "id=?", new String[]{String.valueOf(note.getId())});
    }

}
