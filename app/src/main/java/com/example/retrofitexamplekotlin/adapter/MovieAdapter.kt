package com.example.retrofitexamplekotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexamplekotlin.OnItemClickListener
import com.example.retrofitexamplekotlin.R
import com.example.retrofitexamplekotlin.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Context, private val movieList: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.layout_movie_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(movieList[position].imageurl).into(holder.image)
        holder.txt_name.text = movieList[position].name
        holder.txt_team.text = movieList[position].team
        holder.txt_createdby.text = movieList[position].createdby

        holder.itemView.setOnClickListener{
            itemClickListener.onItemClick(it,position)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image : ImageView
        var txt_name : TextView
        var txt_team : TextView
        var txt_createdby : TextView

        init {
            image =itemView.findViewById(R.id.image_movie)
            txt_name = itemView.findViewById(R.id.txt_name)
            txt_team = itemView.findViewById(R.id.txt_team)
            txt_createdby = itemView.findViewById(R.id.txt_createdby)
        }
    }

    fun setClickListener(onItemClickListener: OnItemClickListener){
        itemClickListener = onItemClickListener
    }
}