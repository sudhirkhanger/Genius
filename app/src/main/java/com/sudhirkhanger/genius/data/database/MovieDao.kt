package com.sudhirkhanger.genius.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun loadAllMovies(): LiveData<List<MovieEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntry: MovieEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movieEntry: MovieEntry)

    @Delete
    fun delete(movieEntry: MovieEntry)

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun findMovieById(movieId: Int): LiveData<MovieEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(vararg movieEntry: MovieEntry?)

    @Query("DELETE FROM movie")
    fun deleteOldData()
}