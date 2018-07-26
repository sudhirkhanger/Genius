package com.sudhirkhanger.genius.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(entities = [MovieEntry::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class MovieDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}