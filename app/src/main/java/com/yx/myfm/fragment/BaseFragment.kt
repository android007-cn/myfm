package com.yx.myfm.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-06-14 15:08
 * @Email:       309032663@qq.com
 */
open class BaseFragment() : Fragment() {
    lateinit var mContext: Context
    protected lateinit var mainHandler: WithoutLeakHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainHandler = WithoutLeakHandler(this)
    }

    protected open fun mainHandlerMessage(baseFragment: BaseFragment?, msg: Message) {}

    companion object {
        class WithoutLeakHandler(baseFragment: BaseFragment) : Handler() {
            private var baseFragment: WeakReference<BaseFragment> = WeakReference(baseFragment)

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val myFragment = baseFragment.get()
                myFragment?.mainHandlerMessage(myFragment, msg)
            }
        }
    }

}