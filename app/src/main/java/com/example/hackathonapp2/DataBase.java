package com.example.hackathonapp2;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;


public class DataBase extends SQLiteOpenHelper {
    //initalises the nesisary variables for the data base
    private static final String DB_name = "HackathonDB";
    private static final int DB_Version =1;
    private static final String TABLE_NAME = "hackathonTable";
    private static final String EXP_AMOUNT = "EXPAMOUNT";
    private static final String LEVEL = "LEVEL";
    private static final String HIGH_SCORE = "highscore";



    // below variable is for our id column.
    private static final String ID_COL = "id";

    public DataBase(@Nullable Context context) {
        //sets up the inital sql database
        super(context, DB_name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creates a table in the sql database, with the columbs of nessisary variables
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EXP_AMOUNT + " INTEGER,"
                + LEVEL + " INTEGER,"
                + HIGH_SCORE + " INTEGER)";
        db.execSQL(query);
    }


    public void addDATA(int exp, int level, int highscore){
        //inputs the nessisary data into a new row of the data table

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(EXP_AMOUNT, exp);
        values.put(LEVEL, level);
        values.put(HIGH_SCORE,highscore);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public boolean isEmpty(){
        //checks if the database is empty or not, by checking the row position of the last line in the table
        SQLiteDatabase db =this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToLast();
        return (cursor.getCount() == 0);
    }
    public ArrayList<String> getDATA(){
        //outputs the last row of data

        SQLiteDatabase db =this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToLast();

        ArrayList<String> lastline = new ArrayList<String>();
        lastline.add(cursor.getString(1));
        lastline.add(cursor.getString(2));
        lastline.add(cursor.getString(3));

        return lastline;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
