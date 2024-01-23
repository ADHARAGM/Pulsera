package com.ces.pulsera.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ces.pulsera.data.remote.model.ResponseMac;

@Database(entities ={ResponseMac.class}, version = 1, exportSchema = false  )
public abstract class MacPersonaDatabase extends RoomDatabase {
    public abstract MacDao getMacDao();


}


