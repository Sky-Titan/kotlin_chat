package org.techtown.kotlinchat.Fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_my_chat_fragment.*

import org.techtown.kotlinchat.R

class FragmentMyChat : Fragment() {

    companion object {
        fun newInstance() = FragmentMyChat()
    }
    private lateinit var myView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView =  inflater.inflate(R.layout.fragment_my_chat_fragment, container, false)


        return myView
    }

}
