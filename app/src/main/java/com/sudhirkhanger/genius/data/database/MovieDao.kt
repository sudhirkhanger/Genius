package com.sudhirkhanger.genius.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun loadAllMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun findMovieById(movieId: Int): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(vararg movie: Movie?)

    @Query("DELETE FROM movie")
    fun deleteOldData()
}