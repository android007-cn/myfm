package com.yx.myfm.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yx.myfm.R

/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-06-17 13:00
 * @Email:       309032663@qq.com
 */
class CategoryFrag : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.category_frag_layout, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object {

        private const val TAG = "CategoryFrag"

        fun newInstance(): CategoryFrag {
            return CategoryFrag()
        }
    }
}