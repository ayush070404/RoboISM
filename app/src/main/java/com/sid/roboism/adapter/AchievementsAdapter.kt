package com.sid.roboism.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sid.roboism.R
import com.sid.roboism.data.AchievementsDataModel


class AchievementsAdapter(
    private val achievementsList: List<AchievementsDataModel>
) :
    RecyclerView.Adapter<AchievementsAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.achievements_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return achievementsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val currentData = achievementsList[position]

        if (currentData.image != null) {
            Glide.with(holder.itemView)
                .load(currentData.image)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.progress_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .centerCrop()
                .into(holder.ach_image)
        }
        holder.name.text = currentData.title
        holder.participants.text = currentData.participants
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.ach_name)
        val participants = view.findViewById<TextView>(R.id.ach_participants)
        val ach_image = view.findViewById<ImageView>(R.id.ach_img)
    }
}