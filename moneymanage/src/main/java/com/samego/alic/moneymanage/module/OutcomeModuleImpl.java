package com.samego.alic.moneymanage.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.samego.alic.moneymanage.bean.Outcome;
import com.samego.alic.moneymanage.common.AppConfig;
import com.samego.alic.moneymanage.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 支出OutcomeModule
 * Created by alic on 16-9-21.
 */
public class OutcomeModuleImpl implements OutcomeModule {
    @Override
    public void addOutcome(Context context, Outcome outcome) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
        //生成ContentValues对象，key:列名  value:想插入的值
        ContentValues values = new ContentValues();
        values.put("username", outcome.getUsername());
        values.put("title", outcome.getTitle());
        values.put("datetime", outcome.getDatetime());
        values.put("money", String.valueOf(outcome.getMoney()));
        values.put("place", outcome.getPlace());
        values.put("description", outcome.getDescription());
        writableDatabase.insert(AppConfig.TABLE_OUTCOME_NAME, null, values);
        AppUtil.LogMessage("成功插入支出信息一条~~~");
    }

    @Override
    public void deleteOutcome(Context context, Outcome outcome) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
        writableDatabase.delete(AppConfig.TABLE_OUTCOME_NAME, "id=?", new String[]{String.valueOf(outcome.getId())});
    }

    @Override
    public void modifyOutcome(Context context, Outcome outcome) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", outcome.getUsername());
        values.put("title", outcome.getTitle());
        values.put("datetime", outcome.getDatetime());
        values.put("money", String.valueOf(outcome.getMoney()));
        values.put("place", outcome.getPlace());
        values.put("description", outcome.getDescription());

        writableDatabase.update(AppConfig.TABLE_OUTCOME_NAME, values, "id=?", new String[]{String.valueOf(outcome.getId())});
    }

    @Override
    public List<Outcome> getOutcome(Context context, String username) {
        List<Outcome> outcomes = new ArrayList<>();
        //test
//        for (int index = 0; index<5 ; index++){
//            Outcome outcome = new Outcome();
//            outcome.setId(index);
//            outcome.setTitle("嗨~我是投资");
//            outcome.setDescription("7777777");
//            outcome.setMoney("6666");
//            outcome.setPlace("新上海");
//            outcome.setDatetime("2016-09-18");
//            outcomes.add(outcome);
//        }
        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context)
        );
        SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                AppConfig.TABLE_OUTCOME_NAME, null, "username=?", new String[]{username}, null, null, null, null
        );
        while (cursor.moveToNext()){
            Outcome outcome = new Outcome();
            outcome.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            outcome.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            outcome.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            outcome.setDatetime(cursor.getString(cursor.getColumnIndex("datetime")));
            outcome.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            outcome.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            outcome.setPlace(cursor.getString(cursor.getColumnIndex("place")));
            outcomes.add(outcome);
        }
        cursor.close();
        return outcomes;
    }
}
