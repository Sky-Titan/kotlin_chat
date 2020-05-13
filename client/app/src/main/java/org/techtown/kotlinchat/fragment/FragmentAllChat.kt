package org.techtown.kotlinchat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import org.techtown.kotlinchat.R
import org.techtown.kotlinchat.databinding.FragmentAllChatFragmentBinding

class FragmentAllChat : Fragment() {

    companion object {
        fun newInstance() = FragmentAllChat()
    }

    private lateinit var viewModel: AllChatViewModel
    private lateinit var binding : FragmentAllChatFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentAllChatFragmentBinding.inflate(inflater,container,false)

        viewModel = AllChatViewModel()

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }
}
