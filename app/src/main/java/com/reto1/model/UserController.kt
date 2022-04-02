package com.reto1.model

import kotlin.properties.Delegates

class UserController {

    private val users = ArrayList<User>()
    private var activeUserId = -1
    private lateinit var activeUser: User

    constructor() {
        users.add(User("alpha@gmail.com", "aplicacionesmoviles"))
        users.add(User("beta@gmail.com", "aplicacionesmoviles"))
    }

    fun addUser(newUser:User) {
        users.add(newUser)
    }

    fun getUsers() :ArrayList<User> {
        return users
    }

    fun verifyUser(mail:String , pass:String) : Int {
        var ret = -1
        for (i in 0 until users.size) {
            if (users[i].mail.equals(mail) && users[i].pass.equals(pass))
                ret = i;
        }
        return ret
    }

    fun setUpActualUser(user: Int) {
        activeUser = users[user]
        activeUserId = user
    }

    fun getActualUser(): User {
        return activeUser
    }

    fun getActualUserId(): Int {
        return activeUserId
    }

    fun searchUser(searchUser: Int): User {
        return users[searchUser]
    }
}