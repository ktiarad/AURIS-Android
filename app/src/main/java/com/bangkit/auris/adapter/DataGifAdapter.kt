package com.bangkit.auris.adapter

import android.provider.Settings.Global.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.auris.R
import com.bangkit.auris.utils.DataDictionary
import com.bumptech.glide.Glide
import com.bangkit.auris.databinding.ItemDictionaryBinding
import com.bangkit.auris.databinding.ItemDictionaryGifBinding
import com.bangkit.auris.utils.DataDictionaries

class DataGifAdapter : RecyclerView.Adapter<DataGifAdapter.ViewHolder>() {

    private var listDataLibrary = mutableListOf<DataDictionary>() // MutableList<DataDictionary>()

    fun setDataLibrary(dictionary: MutableList<DataDictionary>){
        if (dictionary == null) return
        this.listDataLibrary.clear()
        this.listDataLibrary.addAll(dictionary)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLibraryBinding = ItemDictionaryGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemLibraryBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val library = listDataLibrary[position]
        holder.bind(library)
    }

    override fun getItemCount(): Int = listDataLibrary.size

    class ViewHolder(private val binding: ItemDictionaryGifBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dictionary: DataDictionary){
            Log.d("menu gif", "binding here")
            with(binding){
                tvTitle.text = dictionary.title
//                Glide.with(itemView.context)
//                    .load(dictionary.image)
//                    .into(ivLibrary)
                Glide.with(itemView.context)
                    .load(dictionary.image)
                    .into(ivLibrary)
//                gifDrawable = gifImageView.drawable
            }
        }

    }
}