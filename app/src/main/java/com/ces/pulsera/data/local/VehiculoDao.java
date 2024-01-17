package com.ces.pulsera.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface VehiculoDao {
    @Query("Select * from vehiculo")
    LiveData<List<ResponseVehiculo>> loadVehiculos();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveVehiculo(List<ResponseVehiculo>responseVehiculo);
}
