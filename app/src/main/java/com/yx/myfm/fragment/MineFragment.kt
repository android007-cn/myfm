package com.yx.myfm.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yx.myfm.R
import com.yx.myfm.activities.DownloadActivity
import com.yx.myfm.activities.HistoryActivity
import com.yx.myfm.activities.SettingActivity
import kotlinx.android.synthetic.main.fragment_mine_layout.*

class MineFragment : BaseFragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_mine_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        recent_listen_btn.setOnClickListener(this)
        my_download_btn.setOnClickListener(this)
        my_like_btn.setOnClickListener(this)
        my_setting_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.recent_listen_btn -> {
                val intent = Intent(mContext, HistoryActivity::class.java)
                this.startActivity(intent)
            }
            R.id.my_download_btn -> {
                val downLoadIntent = Intent(mContext, DownloadActivity::class.java)
                this.startActivity(downLoadIntent)
            }
            R.id.my_like_btn -> {

            }
            R.id.my_setting_btn -> {
                this.startActivity(Intent(mContext, SettingActivity::class.java))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object {
        private const val TAG = "MineFragment"

        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }
}
