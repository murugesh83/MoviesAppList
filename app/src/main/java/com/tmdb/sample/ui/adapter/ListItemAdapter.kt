package com.tmdb.sample.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tmdb.sample.R
import com.tmdb.sample.data.PopularItem
import com.tmdb.sample.module.ItemClick
import javax.inject.Inject

class ListItemAdapter @Inject constructor(
    private val picasso: Picasso
) :
    RecyclerView.Adapter<ListItemAdapter.ItemViewHolder>() {
    var listItem: ArrayList<PopularItem> = ArrayList()
    private lateinit var itemClick: ItemClick

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.title)
        val overview: TextView = view.findViewById(R.id.overview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (listItem != null) listItem.size else 0
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (listItem?.size > position) {
            holder.title.text = listItem[position].title
            holder.overview.text = listItem[position].overview
            picasso.load("https://image.tmdb.org/t/p/w500/${listItem[position].posterPath}")
                .into(holder.imageView)
            holder.itemView.setOnClickListener {
                Log.d("ListItemAdapter", "item clicked : ${listItem[position].id}")
                itemClick.onItemClicked(listItem[position])
            }
        }
    }

    fun setCallBack(click: ItemClick) {
        itemClick = click
    }

    fun clear() {
        listItem.clear()
    }

    fun setList(list: ArrayList<PopularItem>?) {
        if (list != null)
            listItem.addAll(list)
        notifyDataSetChanged()
    }
}