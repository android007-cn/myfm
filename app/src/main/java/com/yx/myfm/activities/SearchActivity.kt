package com.yx.myfm.activities

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import com.yx.myfm.R
import com.yx.myfm.fragment.SearchHotWordFragment
import com.yx.myfm.fragment.SearchTabPagerFragment
import com.yx.myfm.fragment.SearchWords
import kotlinx.android.synthetic.main.activity_search.*

/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-06-14 20:06
 * @Email:       309032663@qq.com
 */
class SearchActivity : BaseActivity(), SearchView.OnQueryTextListener, View.OnTouchListener, SearchWords {

    private var mImm: InputMethodManager? = null
    private var queryString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //避免布局被键盘推上来
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_search)

        setSupportActionBar(search_toolbar)
        //禁止显示默认 title
        supportActionBar?.setDisplayShowTitleEnabled(false)
        search_toolbar.setNavigationOnClickListener { finish() }

        //设置搜索框提示，去除左边搜索图标
        id_search_view.queryHint = getString(R.string.search_hint)
        id_search_view.isIconified = false
        //隐藏底部横线
        val viewById:View? = id_search_view.findViewById(R.id.search_plate)
        viewById?.setBackgroundColor(Color.TRANSPARENT)
        id_search_view.setOnQueryTextListener(this)

        val searchHotWordFragment = SearchHotWordFragment()
        searchHotWordFragment.searchWords(this@SearchActivity)
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.search_frame, searchHotWordFragment)
        ft.commit()

        mImm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        search_btn.setOnClickListener {
            if(id_search_view.query.isNotEmpty()){
                Log.d(TAG,"开始搜索")
                hideInputManager()
                val ftt = supportFragmentManager.beginTransaction()
                val fragment = SearchTabPagerFragment.newInstance(0, id_search_view.query.toString())
                ftt.replace(R.id.search_frame, fragment).commitAllowingStateLoss()
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        hideInputManager()
        if(query != null){
            Log.d(TAG,"开始搜索")
            val ft = supportFragmentManager.beginTransaction()
            val fragment = SearchTabPagerFragment.newInstance(0, query)
            ft.replace(R.id.search_frame, fragment).commitAllowingStateLoss()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.equals(queryString)) return true
        queryString = newText

        return true
    }

    override fun onSearch(t: String) {
        id_search_view.setQuery(t, true)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        hideInputManager()
        return false
    }

    private fun hideInputManager() {
        mImm?.hideSoftInputFromWindow(id_search_view.windowToken, 0)
        id_search_view.clearFocus()

        //添加搜索记录
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    companion object{
        private const val TAG = "SearchActivity"
    }
}
