package com.ces.pulsera.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Update
import com.ces.pulsera.data.pojo.ResponseMac

@Dao
interface ListMacDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMac(mac:ResponseMac)

   @Delete
   suspend fun deleteMac(mac:ResponseMac)

   @Query("SELECT * FROM macInformation")
   fun getMac():LiveData<List<ResponseMac>>
}