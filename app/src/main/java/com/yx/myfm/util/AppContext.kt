package com.yx.myfm.util

import android.content.Context

/**
 * 全局的API接口。
 *
 */
object AppContext {


    /**
     * 获取全局Context，在代码的任意位置都可以调用，随时都能获取到全局Context对象。
     *
     * @return 全局Context对象。
     */
    lateinit var context: Context

    /**
     * 返回当前应用的包名。
     */
    val packageName: String
        get() = context.packageName

    /**
     * 初始化接口。这里会进行应用程序的初始化操作，一定要在代码执行的最开始调用。
     *
     * @param c
     * Context参数，注意这里要传入的是Application的Context，千万不能传入Activity或者Service的Context。
     */
    fun initialize(c: Context) {
        context = c
    }

}