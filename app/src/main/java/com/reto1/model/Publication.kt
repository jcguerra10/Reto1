package com.reto1.model

import android.net.Uri

class Publication {
    var image: String
    var description: String
    var ciudad: String
    var author: String

    constructor(image:String, description:String, ciudad:String, author:String) {
        this.image = image
        this.description = description
        this.ciudad = ciudad
        this.author = author
    }
}