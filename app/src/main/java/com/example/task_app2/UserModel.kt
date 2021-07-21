package com.example.task_app2

import kotlin.random.Random as Random

data class UserModel(
    var  id : Int = getAutoID(),
    var name: String ="",
    var  mobile: String = ""
) {
    companion object{
        fun getAutoID(): Int {
        val random = java.util.Random()
        return random.nextInt(100)

    }
}
}

