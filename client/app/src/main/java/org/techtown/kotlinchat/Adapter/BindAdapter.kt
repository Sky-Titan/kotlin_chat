package org.techtown.kotlinchat.Adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BindAdapter {


    companion object
    {
        @BindingAdapter("bind:verAdapter")
        fun verAdapter(recyclerView: RecyclerView, adapter: RAdapter) {

            recyclerView.adapter = adapter
        }
    }


}