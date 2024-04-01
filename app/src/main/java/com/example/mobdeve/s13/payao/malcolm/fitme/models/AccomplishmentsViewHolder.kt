package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R




class AccomplishmentViewHolder(accomplishmentView: View) : RecyclerView.ViewHolder(accomplishmentView) {

    private val titleTextView: TextView = accomplishmentView.findViewById(R.id.titleTextView)
    private val badgeImageView: ImageView = accomplishmentView.findViewById(R.id.badgeImg)

    fun bind(accomplishment: Accomplishment, context: Context) {
        titleTextView.text = accomplishment.title
        // Set badge image
        badgeImageView.setImageResource(accomplishment.badgeImg)
        // Adjust ImageView dimensions to match the original layout
        val layoutParams = badgeImageView.layoutParams
        layoutParams.width = context.resources.getDimensionPixelSize(R.dimen.badge_image_width)
        layoutParams.height = context.resources.getDimensionPixelSize(R.dimen.badge_image_height)
        badgeImageView.layoutParams = layoutParams
    }
}