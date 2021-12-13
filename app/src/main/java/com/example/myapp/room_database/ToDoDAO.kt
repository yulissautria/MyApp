package com.example.myapp.room_database

import androidx.room.*
import com.example.myapp.Task

@Dao
interface ToDoDAO {
    @Query("Select * from ToDo")
    suspend fun getAllTasks(): List<ToDo>

    @Query("SELECT * FROM ToDo WHERE id = :id")
    suspend fun findById(id: Int): ToDo

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: ToDo): Long

    @Update
    suspend fun updateTask(task: ToDo)

    @Delete
    suspend fun deleteTask(task: ToDo)

}