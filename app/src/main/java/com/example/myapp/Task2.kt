package com.example.myapp

data class Task2(val id: Int

                 , val name: String, val lastname: String, val identification: String, val phone:String, val address: String) {
    override fun toString(): String {
        return identification
    }
}
