package com.reto1.model

import android.net.Uri
import java.sql.Timestamp
import java.util.*

class Publication {
    var image: String
    var description: String
    var ciudad: String
    var author: String
    var time: String

    constructor(image:String, description:String, ciudad:String, author:String, time: String) {
        this.image = image
        this.description = description
        this.ciudad = ciudad
        this.author = author
        this.time = time
    }
}