package com.example.speedinternet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String  DATABASE_NAME = "malumotlar";
    private static final String  TABLE_NAME = "bazasi";

    public static final String Col_1 = "ID";
    public static final String Col_2 = "YOZUV";
    public static final String Col_3 = "RAQAM";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,YOZUV TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }
    //funksiya o`qish
    public Cursor oqish(){
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor cursor = db1.rawQuery("Select * from " + TABLE_NAME,null);
        return cursor;
    }
    //funksiya o`zgartirish
    public Boolean ozgartir(String id,String yozuv,String kun){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,yozuv);
        int result = sqLiteDatabase.update(TABLE_NAME,contentValues,"ID =?",new String[]{id});
        if (result>0){
            return true;
        }
        else {
            return false;
        }

    }
    //funksiya o`chirihs
    public Integer ochir(String id){
        SQLiteDatabase ddb = this.getWritableDatabase();
        int i = ddb.delete(TABLE_NAME,"ID =?",new String[]{id});
        return i;
    }

    public Boolean kiritish(String yozuv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,yozuv);
        //contentValues.put(Col_3,raqam);
        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }

}
