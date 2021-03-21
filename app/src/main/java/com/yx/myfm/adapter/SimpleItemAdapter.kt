package com.yx.myfm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yx.myfm.R

/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-07-19 11:44
 * @Email:       309032663@qq.com
 */
class SimpleItemAdapter(private var mContext: Context, list: List<String>) : RecyclerView.Adapter<SimpleItemAdapter.ViewHolder>() {
    private var itemList: List<String> = list
    private lateinit var itemClickListener: IKotlinItemClickListener

    class ViewHolder(var simpleItemView: View) : RecyclerView.ViewHolder(simpleItemView) {
        var textViewTitle: TextView = simpleItemView.findViewById(R.id.item_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.simple_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewTitle.text = itemList[position]
        //首行特殊颜色
        if(position == 0) holder.textViewTitle.setTextColor(mContext.resources.getColor(R.color.tab_selected,null))

        holder.simpleItemView.setOnClickListener {
            itemClickListener.onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(position: Int)
    }
}