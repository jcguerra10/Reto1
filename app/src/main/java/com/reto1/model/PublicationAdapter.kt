package com.reto1.model

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reto1.R
import java.net.URI
import java.util.*
import kotlin.collections.ArrayList

class PublicationAdapter: RecyclerView.Adapter<PublicationView>(), OnDeletePublication {

    private lateinit var publicationController : PublicationController
    private lateinit var userController: UserController
    private var publications = ArrayList<Publication>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationView {
        publications = publicationController.getPublications()
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.item_publication, parent, false)
        val publicationView = PublicationView(row)
        return publicationView
    }

    override fun onBindViewHolder(holder: PublicationView, position: Int) {
        val publication = publications[position]
        holder.listener = this
        holder.publication = publication

        val uri = Uri.parse(publication.image)
        holder.publiView.setImageURI(uri)
        holder.descView.setText(publication.description)
        holder.ciudView.setText(publication.ciudad)
        holder.authView.setText(userController.searchUser(publication.author.toInt()).name)
        holder.timeView.setText(publication.time)
    }

    override fun getItemCount(): Int {
        return publications.size
    }

    override fun onDelete(publication: Publication?) {
        Log.d("onDelete ", publication?.author.toString())
    }

    fun setPublications(publicationController: PublicationController, userController: UserController) {
        this.publicationController = publicationController
        this.userController = userController
    }

    fun refresh() {
        publications = publicationController.getPublications();
    }
}