package com.demo.example.FavoriteDb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Alarm")
public class Alarm {
    @PrimaryKey(autoGenerate = true)
    public int aid = 0;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "isAmPm")
    public String isAmPm = "1";

    @ColumnInfo(name = "sound")
    public String sound;

    @ColumnInfo(name = "vibration")
    public String vibration="1";

    @ColumnInfo(name = "Snooze")
    public String Snooze="1";

    @ColumnInfo(name = "SnoozeTime")
    public int SnoozeTime;

    @ColumnInfo(name = "isOn")
    public String isOn="1";

    @ColumnInfo(name = "isSound")
    public String isSound;

    public Alarm(String name,String date, String time, String isAmPm, String sound, String vibration, String snooze,int SnoozeTime,String isOn,String isSound) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.isAmPm = isAmPm;
        this.sound = sound;
        this.vibration = vibration;
        this.Snooze = snooze;
        this.SnoozeTime = SnoozeTime;
        this.isOn = isOn;
        this.isSound = isSound;
    }
    Alarm(){
    }
}