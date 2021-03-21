package com.yx.myfm.adapter

import android.content.Context
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
import com.yx.myfm.util.AppContext
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio

/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-07-18 14:38
 * @Email:       309032663@qq.com
 */
class HoriRadioAdapter(private var mContext: Context, list: List<Radio>) : RecyclerView.Adapter<HoriRadioAdapter.ViewHolder>() {
    private var radioList: List<Radio> = list
    private lateinit var itemClickListener: IKotlinItemClickListener

    class ViewHolder(var radioItemView: View) : RecyclerView.ViewHolder(radioItemView) {
        var imageCover: ImageView = radioItemView.findViewById(R.id.id_image_cover)
        var textViewTitle: TextView = radioItemView.findViewById(R.id.id_title_tv)
        var textViewIntro: TextView = radioItemView.findViewById(R.id.id_singer_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.vertical_radio_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = radioList[position]
        with(item){
            holder.textViewTitle.text = radioName
            holder.textViewIntro.text = AppContext.context.getString(R.string.now_playing,programName)
            Glide.with(mContext)
                    .load(coverUrlLarge).apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                    .into(holder.imageCover)

        }
        holder.radioItemView.setOnClickListener {
            itemClickListener.onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return radioList.size
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