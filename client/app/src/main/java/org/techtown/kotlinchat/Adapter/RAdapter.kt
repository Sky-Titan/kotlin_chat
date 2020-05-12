package org.techtown.kotlinchat.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.techtown.kotlinchat.Fragment.AllChatViewModel
import org.techtown.kotlinchat.databinding.RecyclerviewItemBinding

class RAdapter(viewModel: AllChatViewModel) : RecyclerView.Adapter<RAdapter.MyViewHolder>() {

    private var viewModel : AllChatViewModel
    init {
        this.viewModel = viewModel
    }
    class MyViewHolder : RecyclerView.ViewHolder
    {
        var binding : RecyclerviewItemBinding

        constructor(binding: RecyclerviewItemBinding) : super(binding.root)  {
            this.binding = binding
        }

        fun bind(viewModel: AllChatViewModel, pos : Int)
        {
            binding.viewModel = viewModel
            binding.pos = pos
            binding.executePendingBindings()
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding : RecyclerviewItemBinding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)

        return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(viewModel, position)
    }

    override fun getItemCount(): Int {

        return viewModel.chatList.size
    }
}