package org.techtown.kotlinchat.fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import org.techtown.kotlinchat.MyApplication
import org.techtown.kotlinchat.adapter.RAdapter
import org.techtown.kotlinchat.async.GetAllChatAsync
import org.techtown.kotlinchat.item.ChatItem



class AllChatViewModel() : ViewModel() {
    var chatList: ObservableArrayList<ChatItem>
    var adapter: RAdapter

    init {

        chatList = MyApplication.INSTANCE.chatList

        adapter = RAdapter(this)

    }


}
