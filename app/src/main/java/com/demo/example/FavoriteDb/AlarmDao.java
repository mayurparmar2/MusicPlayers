package com.demo.example.FavoriteDb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface AlarmDao {

    @Query("SELECT * FROM Alarm")
    List<Alarm> getAll();


    @Query("DELETE FROM Alarm WHERE aid == :aid")
    int deleteFav(String aid);

    @Query("SELECT * FROM Alarm WHERE aid == :aid")
    List<Alarm> getAlarmId(String aid);

    @Insert
     void insertAll(ArrayList<Alarm> alarms);

    @Insert
    void insert(Alarm alarm);

    @Delete
    void delete(Alarm alarm);

    @Query("DELETE FROM alarm")
    void TruncateTable();

    @Query("SELECT MAX(aid) FROM Alarm")
    String getLastInsertId();

    @Query("SELECT isOn FROM alarm WHERE aid == :aid")
    String getid(int aid);

    @Query("UPDATE alarm SET isOn = :setIsOn WHERE aid == :aid")
    void updateAlarmIsOn(String setIsOn,String aid);

    @Query("UPDATE alarm SET sound = :setIsOn WHERE aid == :aid")
    void updateAlarmSound(String setIsOn,String aid);

}