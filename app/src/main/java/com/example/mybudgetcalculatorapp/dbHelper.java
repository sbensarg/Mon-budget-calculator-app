package com.example.mybudgetcalculatorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class dbHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "epargne.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "my_epargne";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CAP = "capacite_epargne";
    private static final String COLUMN_TAUX = "taux_epargne";
    private static final String COLUMN_CURRENCY = "currency";
    private static final String create_at = "create_at";
    public dbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CAP + " REAL, " +
                COLUMN_TAUX + " REAL, " +
                COLUMN_CURRENCY + " TEXT, " +
                create_at + " DATETIME DEFAULT CURRENT_DATE);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);

    }
    void addepargne(double cap, double taux, String currency){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CAP, cap);
        cv.put(COLUMN_TAUX, taux);
        cv.put(COLUMN_CURRENCY, currency);
        cv.put(create_at, getDateTime());
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
