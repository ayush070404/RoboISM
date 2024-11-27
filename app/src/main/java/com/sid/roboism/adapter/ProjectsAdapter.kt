package com.sid.roboism.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sid.roboism.R
import com.sid.roboism.data.ProjectsDataModel

class ProjectsAdapter(
    private val context: Context,
    private val projectList: List<ProjectsDataModel>
) :
    RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.project_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val currentData = projectList[position]

        if (currentData.image != null) {
            Glide.with(holder.itemView)
                .load(currentData.image)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.progress_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .centerCrop()
                .into(holder.pr_image)
        }
        val giturl = currentData.github
        val youtubeurl = currentData.video
        if (giturl.isEmpty()) {
            holder.github.alpha = 0.5f
            holder.github.isClickable = false
        }
        if(youtubeurl.isEmpty()){
            holder.youtube.alpha = 0.5f
            holder.youtube.isClickable = false
        }
        holder.name.text = currentData.name
        holder.desc.text = currentData.description

        holder.github.setOnClickListener {
            try {


//                Log.d("Git", url)
                val i = Intent()
                i.setPackage("com.android.chrome")
                i.action = Intent.ACTION_VIEW
                i.data = Uri.parse(giturl)
                ContextCompat.startActivity(it.context, i, null)

            } catch (e: Exception) {
                Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show()

            }
        }
        holder.youtube.setOnClickListener {
            try {
                val i = Intent()
                i.setPackage("com.google.android.youtube")
                i.action = Intent.ACTION_VIEW
                i.data = Uri.parse(youtubeurl)
                ContextCompat.startActivity(it.context, i, null)
            } catch (e: Exception) {
                Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show()

            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.project_name)
        val desc = view.findViewById<TextView>(R.id.project_desc)
        val pr_image = view.findViewById<ImageView>(R.id.project_img)
        val github = view.findViewById<ImageView>(R.id.github)
        val youtube = view.findViewById<ImageView>(R.id.youtube)
    }
}