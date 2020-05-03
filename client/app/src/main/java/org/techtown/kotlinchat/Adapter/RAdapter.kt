package org.techtown.kotlinchat.Adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import org.techtown.kotlinchat.Item.ChatItem
import org.techtown.kotlinchat.R

class RAdapter(private val myDataSet:ArrayList<ChatItem>) : RecyclerView.Adapter<RAdapter.MyViewHolder>() {
    class MyViewHolder : RecyclerView.ViewHolder
    {
        var chatroom_title : TextView
        var chatroom_ID: TextView
        var chatroom_people : TextView
        var context: Context

        constructor(itemView: View, context: Context) :super(itemView) {
            this.chatroom_ID = itemView.findViewById(R.id.chatroom_id)
            this.chatroom_title = itemView.findViewById(R.id.chatroom_title)
            this.chatroom_people = itemView.findViewById(R.id.chatroom_people)
            this.context = context
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val linearLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false) as LinearLayout

        return MyViewHolder(linearLayout,parent.context)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.chatroom_ID.text = myDataSet[position].chatroom_ID
        holder.chatroom_title.text = myDataSet[position].chatroom_title
        holder.chatroom_people.text = myDataSet[position].chatroom_people
    }

    override fun getItemCount(): Int {

        return myDataSet.size
    }
}