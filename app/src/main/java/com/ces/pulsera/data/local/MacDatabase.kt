package com.ces.pulsera.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ces.pulsera.data.pojo.ResponseMac

@Database(entities = [ResponseMac::class], version = 1, exportSchema = false)
abstract class MacDatabase: RoomDatabase() {
    abstract  fun macDao(): ListMacDao
    companion object{
        @Volatile
        var INSTANCE:MacDatabase?=null
        @Synchronized
        fun getInstance(context: Context):MacDatabase{
            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(
                    context,
                    MacDatabase::class.java,
                    "personaMac.db"
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE as MacDatabase
        }
    }
}