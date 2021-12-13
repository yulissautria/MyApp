package com.example.myapp.room_database

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity

data class ToDo (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val code: String,
        val size: String,
        val color: String,
        val type: String,
        val price: String




)