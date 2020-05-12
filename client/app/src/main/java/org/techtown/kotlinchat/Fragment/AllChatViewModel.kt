package org.techtown.kotlinchat.Fragment

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import org.techtown.kotlinchat.Adapter.RAdapter
import org.techtown.kotlinchat.Async.GetAllChatAsync
import org.techtown.kotlinchat.Item.ChatItem



class AllChatViewModel() : ViewModel() {
    var chatList: ObservableArrayList<ChatItem>
    var adapter: RAdapter

    init {
        chatList = ObservableArrayList()
        adapter = RAdapter(this)

    }

    fun onCreate()
    {
        var getAllChatAsync = GetAllChatAsync(chatList)
        getAllChatAsync.execute()

    }
}
