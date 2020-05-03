package org.techtown.kotlinchat.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_all_chat_fragment.*
import org.techtown.kotlinchat.Adapter.RAdapter
import org.techtown.kotlinchat.Item.ChatItem

import org.techtown.kotlinchat.R

class FragmentAllChat : Fragment() {

    companion object {
        fun newInstance() = FragmentAllChat()
    }

    private lateinit var myView : View
    private lateinit var items : ArrayList<ChatItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView =  inflater.inflate(R.layout.fragment_all_chat_fragment, container, false)

        //recycler view item 리스트 지정
        items = ArrayList()
        allchat_recyclerview.adapter = RAdapter(items)
        return myView
    }



}
