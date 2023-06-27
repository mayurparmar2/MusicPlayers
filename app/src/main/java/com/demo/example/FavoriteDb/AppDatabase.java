package com.demo.example.FavoriteDb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Alarm.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlarmDao alarmDao();
}