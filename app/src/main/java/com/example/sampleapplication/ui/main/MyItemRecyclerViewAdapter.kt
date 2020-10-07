package com.example.sampleapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapplication.R
import com.example.sampleapplication.database.ProfileDatabaseEntity
import com.example.sampleapplication.ui.main.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<ProfileDatabaseEntity>?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_favorites, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values?.get(position)
        holder.idView.text = item?.first
        holder.contentView.text = item?.last
        Glide.with(holder.profileImage.context).load(item?.imagaeUrl).into(holder.profileImage)
    }

    override fun getItemCount(): Int = values?.size ?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)
        val profileImage: ImageView = view.findViewById(R.id.iv_profile_small)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}