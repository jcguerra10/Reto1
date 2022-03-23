package com.reto1.model

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserAdapter {

    private val userController = UserController()
    private var users = ArrayList<User>()

    init {
        users = userController.getUsers()
    }
}