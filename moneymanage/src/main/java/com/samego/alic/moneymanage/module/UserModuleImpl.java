package com.samego.alic.moneymanage.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.samego.alic.moneymanage.bean.User;
import com.samego.alic.moneymanage.common.AppConfig;
import com.samego.alic.moneymanage.module.listener.OnLoginListener;
import com.samego.alic.moneymanage.module.listener.OnModifyPSDListener;
import com.samego.alic.moneymanage.utils.AppUtil;
import com.samego.alic.moneymanage.utils.DataUtil;

/**
 *
 * Created by alic on 16-9-14.
 */
public class UserModuleImpl implements UserModule {
    @Override
    public void login(Context context,String username, String password, OnLoginListener onLoginListener) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context)
        );
        SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query(
                AppConfig.TABLE_USER_NAME, null, "username=?", new String[]{username}, null, null, null, null
        );
        //注意这里，指针并没有指向第一个
        cursor.moveToNext();
        if (cursor.getCount()<=0){
            onLoginListener.loginFailed();
            return;
        }
        if(cursor.getString(cursor.getColumnIndex("username")).equals(username) && cursor.getString(cursor.getColumnIndex("password")).equals(DataUtil.getMD5(password))){
            onLoginListener.loginSuccess();
        }else {
            onLoginListener.loginFailed();
        }
        cursor.close();
    }

    @Override
    public void modifyPassword(Context context, User user, String newPwd, OnModifyPSDListener onModifyPSDListener) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = readableDatabase.query(AppConfig.TABLE_USER_NAME, null, "username=?", new String[]{user.getUsername()}, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToNext();
//            System.out.println("数据库密码" + cursor.getString(cursor.getColumnIndex("password")));
//            System.out.println("输入mo密码"+DataUtil.getMD5(newPwd));
                if (cursor.getString(cursor.getColumnIndex("password")).equals(DataUtil.getMD5(user.getPassword()))) {
                    ContentValues values = new ContentValues();
                    values.put("username", user.getUsername());
                    values.put("password", DataUtil.getMD5(newPwd));
                    int update = writableDatabase.update(AppConfig.TABLE_USER_NAME, values, "username=?", new String[]{user.getUsername()});
                    if (update != 0)
                        onModifyPSDListener.success();
                    else
                        onModifyPSDListener.failed();
                } else {
                    onModifyPSDListener.failed();
                }
        }
        cursor.close();
    }

    @Override
    public void defaultUser(Context context) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, AppConfig.DATABASE_NAME, null, AppUtil.getVersionCode(context));
        SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();
        SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = readableDatabase.query(AppConfig.TABLE_USER_NAME, null, "username=?", new String[]{"alic"}, null, null, null, null);
        if(cursor.getCount()==0){
            //生成ContentValues对象，key:列名  value:想插入的值
            ContentValues values = new ContentValues();
            values.put("username", AppConfig.DEFAULT_USERNAME);
            values.put("password", DataUtil.getMD5(AppConfig.DEFAULT_USERNAME));
            //参数1：表名
            //参数2：如果你想插入空值，那么你必须指定它的所在的列
            //参数3：ContentValues对象
            writableDatabase.insert(AppConfig.TABLE_USER_NAME, null, values);
            AppUtil.LogMessage("写进一条数据--用户信息 username->alic password->admin");
        }
        cursor.close();
    }

}
