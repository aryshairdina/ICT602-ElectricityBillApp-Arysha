package com.example.ict602_electricitybillapp_arysha;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "ElectricityDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Bill(id INTEGER PRIMARY KEY AUTOINCREMENT, month TEXT, units INTEGER, rebate REAL, total REAL, final REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Bill");
        onCreate(db);
    }

    public void insertBill(String month, int units, double rebate, double total, double finalCost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("month", month);
        values.put("units", units);
        values.put("rebate", rebate);
        values.put("total", total);
        values.put("final", finalCost);
        db.insert("Bill", null, values);
    }

    public Cursor getAllBills() {
        return getReadableDatabase().rawQuery("SELECT * FROM Bill", null);
    }
}

