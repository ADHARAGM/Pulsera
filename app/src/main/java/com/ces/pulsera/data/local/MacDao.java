package com.ces.pulsera.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ces.pulsera.data.remote.model.ResponseMac;

import java.util.List;

@Dao
public interface MacDao {
    @Query("Select * from mac")
    LiveData<List<ResponseMac>> loadMac();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMac(List<ResponseMac>responseMac);
}
