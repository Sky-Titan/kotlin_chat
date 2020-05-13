package org.techtown.kotlinchat.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


object BindAdapter {


        @BindingAdapter("bind:setAdapter")
        @JvmStatic
        fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<RAdapter.MyViewHolder>?) {
            print("실행")
            recyclerView.adapter = adapter
        }



}