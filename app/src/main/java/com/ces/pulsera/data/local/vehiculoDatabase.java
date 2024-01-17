package com.ces.pulsera.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities ={ResponseVehiculo.class}, version = 1, exportSchema = false)
public abstract class vehiculoDatabase extends RoomDatabase {
public abstract VehiculoDao getVehiculoDao();






























}
