package com.example.adityasrivastava.marvelcharacters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adityasrivastava.marvelcharacters.R
import com.example.adityasrivastava.marvelcharacters.models.ResultsItem
import com.example.adityasrivastava.marvelcharacters.viewholders.CharacterVH
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = arrayListOf<ResultsItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.character_item, parent, false)
        return CharacterVH(rootView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mCharacter = list.get(position)
        val mThumbNail = mCharacter.thumbnail
        var viewHolder = holder as CharacterVH
        viewHolder.itemView.tv_name.text = mCharacter.name

        if(mCharacter.description.equals("")){
            viewHolder.itemView.tv_description.text = context.resources.getString(R.string.no_description_available)
        }else{
            viewHolder.itemView.tv_description.text = mCharacter.description
        }

        if(mThumbNail?.path != null && !mThumbNail.path.equals("")){
            Picasso.get().load("${mThumbNail.path}/landscape_amazing.${mThumbNail.extension}")
                .into(viewHolder.itemView.img_holder)
            Picasso.get().load("${mThumbNail.path}/standard_medium.${mThumbNail.extension}")
                .into(viewHolder.itemView.small_img)
        }
    }
}