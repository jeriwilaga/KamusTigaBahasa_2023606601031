package com.example.kamustigabahasa_2023606601031.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kamus.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_WORDS = "words";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_INDONESIAN = "indonesian";
    private static final String COLUMN_ENGLISH = "english";
    private static final String COLUMN_KOREAN = "korean";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_WORDS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INDONESIAN + " TEXT, " +
                COLUMN_ENGLISH + " TEXT, " +
                COLUMN_KOREAN + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    public boolean insertWord(String ind, String eng, String kor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INDONESIAN, ind);
        values.put(COLUMN_ENGLISH, eng);
        values.put(COLUMN_KOREAN, kor);
        long result = db.insert(TABLE_WORDS, null, values);
        return result != -1;
    }

    public Cursor getAllWords() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_WORDS, null);
    }

    public boolean updateWord(int id, String ind, String eng, String kor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INDONESIAN, ind);
        values.put(COLUMN_ENGLISH, eng);
        values.put(COLUMN_KOREAN, kor);
        return db.update(TABLE_WORDS, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean deleteWord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_WORDS, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }
}