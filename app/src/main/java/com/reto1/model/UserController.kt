package com.reto1.model

class UserController {

    private val users = ArrayList<User>()

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

    fun verifyUser(mail:String , pass:String) : Boolean {
        var ret = false
        for (u in users) {
            if (u.mail.equals(mail) && u.pass.equals(pass))
                ret = true
        }
        return ret
    }
}