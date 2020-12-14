package com.alaminkarno.rakibwpproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "User.db";
    public static String TABLE_NAME = "User";
    public static String COL_ID = "ID";
    public static String COL_NAME = "NAME";
    public static String COL_PHONE = "PHONE";
    public static String COL_SEATS = "Seats";
    public static String COL_COUNTER = "Counter";
    public static String COL_Destination = "Destination";
    public static int VERSION = 1;

    private String CREATE_TABLE = "create table "+TABLE_NAME+" ("+COL_ID+" Integer primary key autoincrement, "+COL_NAME+" TEXT,"+COL_PHONE+" TEXT,"+COL_SEATS+" TEXT,"+COL_COUNTER+" TEXT,"+COL_Destination+" TEXT)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        onCreate(sqLiteDatabase);
    }


    public long insertData(String name,String phone,String seats,String counter,String destination){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_PHONE,phone);
        contentValues.put(COL_SEATS,seats);
        contentValues.put(COL_COUNTER,counter);
        contentValues.put(COL_Destination,destination);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

        return id;

    }

    public Cursor searchData(int ID){

        String SEARCH_QUERY = "select * from "+TABLE_NAME+" where "+COL_ID+" ="+ID;

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SEARCH_QUERY,null);

        return cursor;

    }
}
