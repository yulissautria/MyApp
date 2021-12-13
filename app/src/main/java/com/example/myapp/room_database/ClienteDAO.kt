package com.example.myapp.room_database

import androidx.room.*

@Dao
interface ClienteDAO {
    @Query("Select * from Cliente")
    suspend fun getAllTasks(): List<ToDo>

    @Query("SELECT * FROM Cliente WHERE identification = :id")
    suspend fun findById(id: Int): Cliente

    @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertTask(task: Cliente): Long

    @Update
    suspend fun updateTask(task: Cliente)

    @Delete
    suspend fun deleteTask(task: Cliente)

}