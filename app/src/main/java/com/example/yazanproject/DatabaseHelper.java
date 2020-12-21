package com.example.yazanproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public  static  final String DATABASE_NAME ="Student.db";
    public  static  final String TABLE_NAME ="Student.table";
    public  static  final String COL_1 ="ID";
    public  static  final String COL_2 ="NAME";
    public  static  final String COL_3 ="DOCTORS";
    public  static  final String COL_4 ="MARK";



    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

      db.execSQL("CREATE TABLE \"Student\" (\n" +
              "\t\"ID\"\tINTEGER,\n" +
              "\t\"name\"\tTEXT,\n" +
              "\t\"doctors\"\tTEXT,\n" +
              "\t\"marks\"\tINTEGER,\n" +
              "\tPRIMARY KEY(\"ID\" )\n" +
              ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
onCreate(db);

    }
    public boolean InsertData(String name , String doctors , String marks)
    {
        SQLiteDatabase db=this .getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,doctors);
        contentValues.put(COL_4,marks);
        long result =db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
            else
                return true;



    }
    public Cursor getAllData(){
        SQLiteDatabase db=this .getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;


    }
}
