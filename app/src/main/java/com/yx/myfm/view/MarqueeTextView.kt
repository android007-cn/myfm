package com.yx.myfm.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView


/**
 * @Description:
 * @Author:      jerry
 * @CreateDate:  2020-07-31 09:50
 * @Email:       309032663@qq.com
 */
class MarqueeTextView : TextView {

    override fun isFocused(): Boolean = true

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
}