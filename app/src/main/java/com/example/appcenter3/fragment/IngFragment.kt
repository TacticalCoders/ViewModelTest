package com.example.appcenter3.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenter3.*

class IngFragment : Fragment() {

    private val allList: AllList by lazy {
        ViewModelProvider(this).get(AllList::class.java)
    }

    lateinit var ingadapter:IngAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("IngFragment","객체 생성됨")
        val rootView = inflater.inflate(R.layout.fragment_ing, container, false)
        val ingList:RecyclerView = rootView.findViewById(R.id.ingList)
        ingList.layoutManager = LinearLayoutManager(rootView.context)

        Log.d("AllList.IngItems의 크기","${allList.IngItems.size}")
        ingadapter = IngAdapter(allList.IngItems)
        ingList.adapter = ingadapter

        return rootView
    }


    inner class IngAdapter(var list: MutableList<ItemData>) : RecyclerView.Adapter<MyViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.list_item,parent,false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val Item = list[position]
            holder.bind(Item)
            holder.itemView.setOnClickListener {
                list.removeAt(position)
                allList.DoneItems.add(ItemData(Item.listText,2,false))
                notifyDataSetChanged()
            }
        }
        override fun getItemCount(): Int = list.size
    }

    override fun onStart() {
        super.onStart()
        Log.d("IngFragment","onStart()실행됨")
    }

    override fun onPause() {
        super.onPause()
        Log.d("IngFragment","onPause()실행됨")
    }

    override fun onResume() {
        super.onResume()
        Log.d("IngFragment","onResume()실행됨")
        Log.d("AllList.IngItems의 크기","${allList.IngItems.size}")
        ingadapter.notifyDataSetChanged()
        Log.d("IngFragment에서 ","notifyDataSetChanged()실행됨")
    }
}