package com.reto1.model

class PublicationController {

    private var publications = ArrayList<Publication>()

    constructor() {
        //publications.add(Publication("", "Descripcion", "Cali", "Juan"))
    }

    fun addPublication(newPublication: Publication) {
        publications.add(newPublication)
    }

    fun getPublications(): ArrayList<Publication> {
        return publications
    }
}