package com.ces.pulsera.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Update
import com.ces.pulsera.data.pojo.ResponseMac

@Dao
interface ListMacDao {

   @Delete
    fun deleteMac(vararg  mac:ResponseMac)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMac(vararg  mac:ResponseMac)

   @Query("SELECT * FROM macInformation")
   fun getMac():LiveData<List<ResponseMac>>
}