package com.reto1.model

import java.io.File

class User {
    var name: String
    var mail: String
    var pass: String

    constructor(mail: String, pass: String) {
        this.mail = mail
        this.pass = pass
        this.name = "DefaultName"
    }
}