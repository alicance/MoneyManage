package com.samego.alic.moneymanage.module;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.samego.alic.moneymanage.common.AppConfig;
import com.samego.alic.moneymanage.utils.AppUtil;

/**
 *
 * Created by alic on 16-9-19.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + AppConfig.TABLE_USER_NAME + "(" +
                        "username VARCHAR(11) PRIMARY KEY," +
                        "password VARCHAR(40)" +
                        ")"
        );
        AppUtil.LogMessage("用户数据表已经创建~~~");

        db.execSQL("CREATE TABLE " + AppConfig.TABLE_NOTE_NAME + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "username VARCHAR(11)," +
                        "title VARCHAR(20)," +
                        "content VARCHAR(100)," +
                        "datetime VARCHAR(20)" +
                        ")"
        );
        AppUtil.LogMessage("便签数据表已经创建~~~");

        db.execSQL("CREATE TABLE " + AppConfig.TABLE_INCOME_NAME + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "title VARCHAR(20)," +
                        "datetime VARCHAR(20)," +
                        "money INTEGER," +
                        "payer VARCHAR(10)," +
                        "description VARCHAR(100)," +
                        "username VARCHAR(11)" +
                        ")"
        );
        AppUtil.LogMessage("收入数据表已经创建~~~");

        db.execSQL("CREATE TABLE " + AppConfig.TABLE_OUTCOME_NAME + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "title VARCHAR(20)," +
                        "datetime VARCHAR(20)," +
                        "money INTEGER," +
                        "place VARCHAR(20)," +
                        "description VARCHAR(100)," +
                        "username VARCHAR(11)" +
                        ")"
        );
        AppUtil.LogMessage("支出数据表已经创建~~~");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("db upgrade");
    }
    /**
     * 关闭数据库1
     * @param writeDatabase writeDatabase
     * @param readDatabase readDatabase
     * @param cursor cursor
     */
    public static void closeDatabase(SQLiteDatabase writeDatabase, SQLiteDatabase readDatabase, Cursor cursor) {
        if (cursor != null)
            cursor.close();
        if (writeDatabase != null)
            writeDatabase.close();
        if (readDatabase != null)
            readDatabase.close();
    }
    /**
     * 关闭数据库2
     * @param writeOrReadDatabase writeOrReadDatabase
     * @param cursor cursor
     */
    public static void closeDatabase(SQLiteDatabase writeOrReadDatabase, Cursor cursor) {
        if (cursor != null)
            cursor.close();
        if (writeOrReadDatabase != null)
            writeOrReadDatabase.close();
    }
    /**
     * 关闭数据库2
     * @param writeOrReadDatabase writeOrReadDatabase
     */
    public static void closeDatabase(SQLiteDatabase writeOrReadDatabase) {
        if (writeOrReadDatabase != null)
            writeOrReadDatabase.close();
    }

}
