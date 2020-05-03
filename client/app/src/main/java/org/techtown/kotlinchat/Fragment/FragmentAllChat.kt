package org.techtown.kotlinchat.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_all_chat_fragment.*
import org.techtown.kotlinchat.Adapter.RAdapter
import org.techtown.kotlinchat.Async.GetAllChatAsync
import org.techtown.kotlinchat.Item.ChatItem

import org.techtown.kotlinchat.R

class FragmentAllChat : Fragment() {

    companion object {
        fun newInstance() = FragmentAllChat()
    }

    private lateinit var myView : View
    lateinit var items : ArrayList<ChatItem>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView =  inflater.inflate(R.layout.fragment_all_chat_fragment, container, false)

        //모든 채팅 정보 가져오기
        var getAllChatAsync = GetAllChatAsync(context!!,this)
        getAllChatAsync.execute()
        return myView
    }

    fun setRecyclerAdater(items : ArrayList<ChatItem>)
    {
        this.items = items
        allchat_recyclerview.adapter = RAdapter(this.items)
    }



}
