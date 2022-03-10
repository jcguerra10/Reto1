package com.reto1.model

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserAdapter {

    private val users = ArrayList<User>()

    init {
        users.add(User("alpha@gmail.com", "aplicacionesmoviles"))
        users.add(User("beta@gmail.com", "aplicacionesmoviles"))
    }

    fun verifyUser(mail:String , pass:String) : Boolean {
        var ret = false
        for (u in users) {
            if (u.mail.equals(mail) && u.pass.equals(pass))
                ret = true
        }
        return ret
    }
}