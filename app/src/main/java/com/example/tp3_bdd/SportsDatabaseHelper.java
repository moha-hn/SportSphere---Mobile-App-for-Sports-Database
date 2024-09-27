package com.example.tp3_bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SportsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sports.db";
    private static final int DATABASE_VERSION = 1;

    public SportsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE sports (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "category TEXT, " +
                "numberOfPlayers INTEGER);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sports");
        onCreate(db);
    }

    public void addSports(Sports sport) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", sport.getName());
        values.put("category", sport.getCategory());
        values.put("numberOfPlayers", sport.getNumberOfPlayers());
        db.insert("sports", null, values);
    }

    public List<Sports> getAllSports() {
        List<Sports> sportsList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("sports", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            int numberOfPlayers = cursor.getInt(cursor.getColumnIndex("numberOfPlayers"));

            sportsList.add(new Sports(id, name, category, numberOfPlayers));
        }
        cursor.close();
        return sportsList;
    }

    public void updateSports(Sports sport) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", sport.getName());
        values.put("category", sport.getCategory());
        values.put("numberOfPlayers", sport.getNumberOfPlayers());

        db.update("sports", values, "id = ?", new String[]{String.valueOf(sport.getId())});
    }

    public void deleteSports(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("sports", "id = ?", new String[]{String.valueOf(id)});
    }
}
