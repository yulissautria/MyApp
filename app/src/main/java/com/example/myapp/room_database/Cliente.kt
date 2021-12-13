package com.example.myapp.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity

data class Cliente(
        @PrimaryKey(autoGenerate = true)


        val name: Int,
        val lastname: String,
        val identification: String,
        val phone: String,
        val address: String,



)