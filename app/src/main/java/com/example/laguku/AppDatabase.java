package com.example.laguku;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Lagu.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //Akses database menggunakan method abstract
    public abstract LaguDAO laguDAO();

    private static AppDatabase appDatabase;
    public static AppDatabase iniDb(Context context){
        if(appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "dbLagu").allowMainThreadQueries().build();
        return appDatabase; }
}
