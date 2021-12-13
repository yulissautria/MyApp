package com.example.myapp.room_database

import androidx.room.*

    @Database(
        entities = arrayOf(ToDo::class),
        version = 1
    )
    abstract class ToDoDatabase: RoomDatabase(){
        abstract fun todoDao(): ToDoDAO
        abstract fun clienteDao(): ClienteDAO

    }

