package com.jino.mygithub.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jino.mygithub.R
import com.jino.mygithub.bean.ui.ReposUIModel

class SearchPageAdapter : PagingDataAdapter<ReposUIModel, SearchPageAdapter.ViewHolder>(COMPARATOR) {
    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<ReposUIModel>() {
            override fun areItemsTheSame(oldItem: ReposUIModel, newItem: ReposUIModel): Boolean {
                return oldItem.repositoryName == newItem.repositoryName
            }

            override fun areContentsTheSame(oldItem: ReposUIModel, newItem: ReposUIModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.tvName.text = it.repositoryName
            holder.tvDesc.text = it.repositoryDes
            holder.tvStar.text = it.repositoryStar
        }

        holder.llItem.setOnClickListener {
            // TODO Item点击事件
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_page, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_desc)
        val tvStar: TextView = itemView.findViewById(R.id.tv_star)
        val llItem: LinearLayout = itemView.findViewById(R.id.ll_item)
    }

}