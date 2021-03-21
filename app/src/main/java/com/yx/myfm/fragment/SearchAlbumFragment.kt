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
import com.yx.myfm.activities.AlbumActivity
import com.yx.myfm.adapter.AlbumAdapter
import com.yx.myfm.util.AppContext
import com.ximalaya.ting.android.opensdk.model.album.Album
import java.util.ArrayList

/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-06-10 20:06
 * @Email:       309032663@qq.com
 */
class SearchAlbumFragment : BaseFragment() {

    private lateinit var mAdapter: AlbumAdapter
    private  var albumsList: ArrayList<Album>?=null
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recylerview, container, false)
        if (arguments != null) {
            albumsList = (arguments as Bundle).getParcelableArrayList("searchAlbum")
        }

        recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView
        layoutManager = LinearLayoutManager(mContext)
        recyclerView.layoutManager = layoutManager
        albumsList?.let {
            mAdapter = AlbumAdapter(AppContext.context, it)
            mAdapter.setOnKotlinItemClickListener(object : AlbumAdapter.IKotlinItemClickListener {
                override fun onItemClickListener(position: Int) {
                    val intent = Intent(context, AlbumActivity::class.java)
                    //传递一个 album
                    intent.putExtra("album", it[position])
                    context?.startActivity(intent)
                }
            })
        }

        recyclerView.adapter = mAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL))

        return view
    }

    companion object {
        fun newInstance(list: ArrayList<Album>): SearchAlbumFragment {
            val fragment = SearchAlbumFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("searchAlbum", list)
            fragment.arguments = bundle
            return fragment
        }
    }
}