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

        println("채팅 아이디${chatList[0].chatroom_ID}")
        println("사이즈 : ${chatList.size}")
        adapter = RAdapter(this)

    }


}
