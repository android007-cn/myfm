package com.yx.myfm.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yx.myfm.R
import com.yx.myfm.activities.PlayingActivity
import com.yx.myfm.adapter.CommonTrackAdapter
import com.yx.myfm.util.AppContext
import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager
import java.util.ArrayList

/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-06-10 20:06
 * @Email:       309032663@qq.com
 */
class SearchTrackFragment : BaseFragment() {

    private lateinit var mAdapter: CommonTrackAdapter
    private lateinit var tracksList: ArrayList<Track>
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recylerview, container, false)
        if (arguments != null) {
            tracksList = (arguments as Bundle).getParcelableArrayList("searchMusic")?:ArrayList()
        }

        recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView

        layoutManager = LinearLayoutManager(mContext)
        recyclerView.layoutManager = layoutManager
        mAdapter = CommonTrackAdapter(AppContext.context, tracksList, true)
        mAdapter.setOnKotlinItemClickListener(object : CommonTrackAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                XmPlayerManager.getInstance(mContext).playList(tracksList, position)
                val intent = Intent(context, PlayingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context?.startActivity(intent)
                //todo:跳转之后将播放列表改为当前声音所在专辑列表
            }
        })
        recyclerView.adapter = mAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL))

        return view
    }

    companion object {
        fun newInstance(list: ArrayList<Track>): SearchTrackFragment {
            val fragment = SearchTrackFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("searchMusic", list)
            fragment.arguments = bundle
            return fragment
        }
    }
}