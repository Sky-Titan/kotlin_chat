package org.techtown.kotlinchat.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import org.techtown.kotlinchat.R
import org.techtown.kotlinchat.databinding.FragmentAllChatFragmentBinding

class FragmentAllChat : Fragment() {

    companion object {
        fun newInstance() = FragmentAllChat()
    }

    private lateinit var myView : View

    private lateinit var recyclerView : RecyclerView
    private lateinit var viewModel: AllChatViewModel
    private lateinit var binding : FragmentAllChatFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_chat_fragment,container,false)
        recyclerView = myView.findViewById(R.id.allchat_recyclerview) as RecyclerView

        viewModel = AllChatViewModel()
        viewModel.onCreate()
        //binding.setVariable()
        return binding.root
    }





}
