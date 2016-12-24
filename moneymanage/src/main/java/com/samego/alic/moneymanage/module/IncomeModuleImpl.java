package com.samego.alic.moneymanage.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.bean.Note;
import com.samego.alic.moneymanage.common.AppConfig;
import com.samego.alic.moneymanage.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 收入Module
 * Created by alic on 16-9-21.
 */
public class IncomeModuleImpl implements IncomeModule {
    @Override
    public void addIncome(Context context, Income income) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
        //生成ContentValues对象，key:列名  value:想插入的值
        ContentValues values = new ContentValues();
        values.put("username", income.getUsername());
        values.put("title", income.getTitle());
        values.put("datetime", income.getDatetime());
        values.put("money", String.valueOf(income.getMoney()));
        values.put("payer", income.getPayer());
        values.put("description", income.getDescription());
        writableDatabase.insert(AppConfig.TABLE_INCOME_NAME, null, values);
        AppUtil.LogMessage("成功插入收入信息一条~~~");
    }

    @Override
    public void deleteIncome(Context context, Income income) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
        writableDatabase.delete(AppConfig.TABLE_INCOME_NAME, "id=?", new String[]{String.valueOf(income.getId())});
    }

    @Override
    public void modifyIncome(Context context, Income income) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", income.getUsername());
        values.put("title", income.getTitle());
        values.put("datetime", income.getDatetime());
        values.put("money", String.valueOf(income.getMoney()));
        values.put("payer", income.getPayer());
        values.put("description", income.getDescription());
        System.out.println("after money is" + income.getMoney());

        writableDatabase.update(AppConfig.TABLE_INCOME_NAME, values, "id=?", new String[]{String.valueOf(income.getId())});
    }

    @Override
    public List<Income> getIncomes(Context context, String username) {
        List<Income> incomes = new ArrayList<>();
        //test
//        for (int index = 0; index<5 ; index++){
//            Income income = new Income();
//            income.setId(index);
//            income.setTitle("嗨~我是投资");
//            income.setDescription("7777777");
//            income.setMoney("6666");
//            income.setDatetime("2016-09-18");
//            incomes.add(income);
//        }
        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context)
        );
        SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                AppConfig.TABLE_INCOME_NAME, null, "username=?", new String[]{username}, null, null, null, null
        );
        while (cursor.moveToNext()){
            Income income = new Income();
            income.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            income.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            income.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            income.setDatetime(cursor.getString(cursor.getColumnIndex("datetime")));
            income.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            income.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            income.setPayer(cursor.getString(cursor.getColumnIndex("payer")));
            incomes.add(income);
        }
        cursor.close();
        return incomes;
    }
}
