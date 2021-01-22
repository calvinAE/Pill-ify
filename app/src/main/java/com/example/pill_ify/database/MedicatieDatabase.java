package com.example.pill_ify.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pill_ify.models.Medication;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MedicatieDatabase extends SQLiteOpenHelper {

    private SQLiteDatabase database;
    private static String DATABASE_NAME = "medication";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public MedicatieDatabase(@Nullable Context context)
    {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }


    //methode om alle data op te halen uit db
    public Cursor readData()
    {
        String query = "SELECT * FROM Medication";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
