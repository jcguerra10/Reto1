package com.reto1.model

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reto1.R
import java.net.URL

class PublicationAdapter: RecyclerView.Adapter<PublicationView>(), OnDeletePublication {

    private val publications = ArrayList<Publication>()

    init {
        publications.add(Publication("", "Soy la maquina", "Cali", "Yo Obvio"))
    }

    fun addPublication(newPublication: Publication) {
        publications.add(newPublication)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationView {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.item_publication, parent, false)
        val publicationView = PublicationView(row)
        return publicationView
    }

    override fun onBindViewHolder(holder: PublicationView, position: Int) {
        val publication = publications[position]
        holder.listener = this
        holder.publication = publication
        holder.descView.setText(publication.description)
        holder.ciudView.setText(publication.ciudad)
        holder.authView.setText((publication.author))
    }

    override fun getItemCount(): Int {
        return publications.size
    }

    override fun onDelete(publication: Publication?) {
        Log.d("onDelete ", publication?.author.toString())
    }
}