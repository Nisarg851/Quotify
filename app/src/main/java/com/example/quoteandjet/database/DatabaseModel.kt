package com.example.quoteandjet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quoteandjet.dao.QuoteDAO
import com.example.quoteandjet.models.Quote

@Database(entities = [Quote::class], version = 1)
abstract class DatabaseModel: RoomDatabase() {
    abstract fun quoteDAO(): QuoteDAO
    companion object{
        private val INSTANCE: DatabaseModel? = null
        fun getDBInstance(applicationContext: Context): DatabaseModel{
            synchronized(this){
                var instance: DatabaseModel? = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        applicationContext,
                        DatabaseModel::class.java,
                        "Database"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return instance!!
            }
        }
    }
}