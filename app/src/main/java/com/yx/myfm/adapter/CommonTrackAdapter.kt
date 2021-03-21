package com.yx.myfm.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.yx.myfm.R
import com.yx.myfm.util.GlobalUtil
import com.ximalaya.ting.android.opensdk.model.track.Track

/**
 * @Description:声音列表适配器
 * @Author:      jerry
 * @CreateDate:  2020-07-26 18:23
 * @Email:       309032663@qq.com
 */
class CommonTrackAdapter(private var mContext: Context, list: List<Track>, private var showImageCover: Boolean) : RecyclerView.Adapter<CommonTrackAdapter.ViewHolder>() {
    private var trackList: List<Track> = list
    private lateinit var itemClickListener: IKotlinItemClickListener

    class ViewHolder(var trackItemView: View) : RecyclerView.ViewHolder(trackItemView) {
        var imageCover: ImageView = trackItemView.findViewById(R.id.id_image_cover)
        var textViewTitle: TextView = trackItemView.findViewById(R.id.id_title_tv)
        var textViewPlayCount: TextView = trackItemView.findViewById(R.id.id_play_count_tv)
        var textViewDuration: TextView = trackItemView.findViewById(R.id.id_duration_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.track_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trackList[position]
        with(item) {
            holder.textViewTitle.text = trackTitle
            holder.textViewPlayCount.text = GlobalUtil.formatNum(playCount.toString(), false)
            holder.textViewDuration.text = DateUtils.formatElapsedTime(duration.toLong())
            if (showImageCover) {
                Glide.with(mContext)
                        .load(coverUrlLarge)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                        .into(holder.imageCover)
            }
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