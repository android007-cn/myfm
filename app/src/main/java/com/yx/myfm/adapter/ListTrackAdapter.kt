package com.yx.myfm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yx.myfm.R
import com.ximalaya.ting.android.opensdk.model.track.Track

/**
 * @Description:弹出列表适配器
 * @Author:      jerry
 * @CreateDate:  2020-07-26 18:23
 * @Email:       309032663@qq.com
 */
class ListTrackAdapter(private var mContext: Context, list: List<Track>) : RecyclerView.Adapter<ListTrackAdapter.ViewHolder>() {
    private var trackList: List<Track> = list
    private lateinit var itemClickListener: IKotlinItemClickListener

    class ViewHolder(var trackItemView: View) : RecyclerView.ViewHolder(trackItemView) {
        var playingFlag: ImageView = trackItemView.findViewById(R.id.id_playing_flag)
        var textViewTitle: TextView = trackItemView.findViewById(R.id.id_title_tv)
        var textViewSinger: TextView = trackItemView.findViewById(R.id.id_singer_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.playing_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trackList[position]
        with(item) {
            holder.textViewTitle.text = trackTitle
            holder.textViewSinger.text = announcer.nickname
        }

        holder.trackItemView.setOnClickListener {
            itemClickListener.onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return trackList.size
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