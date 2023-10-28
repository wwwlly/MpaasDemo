package com.mpaas.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mpaas.demo.R

class MainAdapter(var mContext: Context, val datas: List<MainItemData>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setName(datas[position].name)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(datas[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false))
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView = itemView.findViewById(R.id.tv_name)

        fun setName(text: String) {
            tvName.text = text
        }
    }

    interface OnItemClickListener {
        fun onItemClick(mainItemData: MainItemData)
    }
}

