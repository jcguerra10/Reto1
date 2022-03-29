package com.reto1.model

import android.net.Uri

class User {
    var name: String
    var mail: String
    var pass: String
    var profileImage: String

    constructor(mail: String, pass: String) {
        this.mail = mail
        this.pass = pass
        this.name = "DefaultName"
        this.profileImage = ""
    }
}