package com.example.myapp

data class Task(val id: Int

                ,val code: String, val size: String, val color: String, val type:String, val price: String) {
    val name: CharSequence?

    override fun toString(): String {
        return code
    }
}
