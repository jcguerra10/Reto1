package com.reto1.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reto1.R

interface OnDeletePublication {
    fun onDelete(publication: Publication?)
}

class PublicationView (itemView: View): RecyclerView.ViewHolder(itemView) {
    var listener: OnDeletePublication? = null
    var publication: Publication? = null

    val publiView: ImageView = itemView.findViewById(R.id.publiImg)
    val imagePrfView: ImageView = itemView.findViewById(R.id.imagePro)
    val descView: TextView = itemView.findViewById(R.id.descrTxt)
    val ciudView: TextView = itemView.findViewById(R.id.ciudadTxt)
    val authView: TextView = itemView.findViewById(R.id.authTxt)
    val timeView: TextView = itemView.findViewById(R.id.timeTxt)

}