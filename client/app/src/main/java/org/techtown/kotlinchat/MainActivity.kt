package org.techtown.kotlinchat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import org.techtown.kotlinchat.Adapter.RAdapter
import org.techtown.kotlinchat.Item.RecyclerItem

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RAdapter
    private lateinit var myDataSet: Array<RecyclerItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerItem:RecyclerItem = RecyclerItem();

        recyclerView = findViewById(R.id.recyclerView)

        adapter = RAdapter(myDataSet)

    }

    fun initDataset()
    {
        //TODO : 서버와 통신 후 리스트 불러옴
    }

}
