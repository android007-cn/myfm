package com.yx.myfm.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.yx.myfm.R
import com.yx.myfm.adapter.CommonFragmentPagerAdapter
import com.yx.myfm.fragment.BoutiqueFrag
import com.yx.myfm.fragment.CategoryFrag
import com.yx.myfm.fragment.MineFragment
import com.yx.myfm.service.OnlineFmService
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager
import com.ximalaya.ting.android.opensdk.player.appnotification.NotificationColorUtils
import com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl
import com.ximalaya.ting.android.sdkdownloader.XmDownloadManager
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-06-14 15:06
 * @Email:       309032663@qq.com
 */
class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mPlayerManager: XmPlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initListener()


        //初始化播放器
        mPlayerManager = XmPlayerManager.getInstance(this.applicationContext)

        //初始化通知栏
        //如果贵方的 targetSdkVersion >= 24 需要在 XmNotificationCreater 初始化之前执行下一句
        NotificationColorUtils.isTargerSDKVersion24More = true
        val mNotification = XmNotificationCreater.getInstanse(this)
                .initNotification(this.applicationContext, MainActivity::class.java)
        // 如果之前贵方使用了 `XmPlayerManager.init(int id, Notification notification)` 这个初始化的方式
        // 请参考`4.8 播放器通知栏使用`重新添加新的通知栏布局,否则直接升级可能导致在部分手机播放时崩溃
        // 如果不想使用sdk内部搞好的notification,或者想自建notification 可以使用下面的  init()函数进行初始化
        val notificationId = System.currentTimeMillis().toInt()
        mPlayerManager.init(notificationId, mNotification)

        XmPlayerManager.getInstance(this).init(notificationId,
                XmNotificationCreater.getInstanse(this).createNotification(this,
                        MainActivity::class.java))

        // 此代码表示播放时会去监测下是否已经下载
        XmPlayerManager.getInstance(this).setCommonBusinessHandle(XmDownloadManager.getInstance())

        mPlayerManager.addOnConnectedListerner(object : XmPlayerManager.IConnectListener {
            override fun onConnected() {
                mPlayerManager.removeOnConnectedListerner(this)
                mPlayerManager.playMode = XmPlayListControl.PlayMode.PLAY_MODEL_LIST_LOOP
            }
        })

        val intent = Intent(this, OnlineFmService::class.java)
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")

        XmPlayerManager.release()
        CommonRequest.release()
    }

    private fun initView() {
        main_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        main_tab_layout.setupWithViewPager(main_view_pager)

        val titleList = mutableListOf(getString(R.string.boutique),
                getString(R.string.category),
                getString(R.string.mine))
        val fragmentList = mutableListOf(BoutiqueFrag.newInstance() as Fragment,
                CategoryFrag.newInstance() as Fragment,
                MineFragment.newInstance() as Fragment)
        main_view_pager.adapter = CommonFragmentPagerAdapter(supportFragmentManager, titleList, fragmentList)
        main_view_pager.offscreenPageLimit = 2
    }

    private fun initListener() {
        history_btn.setOnClickListener(this)
        search_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search_btn -> {
                val historyIntent = Intent(this, SearchActivity::class.java)
                this.startActivity(historyIntent)
            }
            R.id.history_btn -> {
                val intent = Intent(this, HistoryActivity::class.java)
                this.startActivity(intent)
            }
        }
    }

    companion object {

        private const val TAG = "MainActivity"

    }

}
