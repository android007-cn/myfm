package com.yx.myfm.db

/**
 * @Description:
 * @Author:      Rickon
 * @CreateDate:  2020-/7/16 6:30 PM
 * @Email:       309032663@qq.com
 */
open class SingletonHolder<T, A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}