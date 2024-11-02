package com.example.lab9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentDB";
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertuserdata(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }
    public Cursor viewAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

    }
    public Boolean updateuserdata(String name, Integer id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        Cursor cursor = DB.rawQuery("Select * from students where name = ?", new
                String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("students", contentValues, "name=?", new
                    String[]{name});
            if (result == -1) {
                return false; } else {
                return true; }
        } else {
            return false;
        } }
    public Boolean deleteData(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from students where name = ?", new
                String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("students", "name=?", new String[]{name});
            if (result == -1) {
                return false; }
            else {
                return true; }
        } else {
            return false;
        } }
}