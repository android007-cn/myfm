/*
 * Copyright (C) guolin, Suzhou Quxiang Inc. Open source codes for study only.
 * Do not use for commercial purpose.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yx.myfm.util

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.text.TextUtils
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


/**
 * 应用程序全局的通用工具类，功能比较单一，经常被复用的功能，应该封装到此工具类当中，从而给全局代码提供方面的操作。
 *
 * @author guolin
 * @since 17/2/18
 */
object GlobalUtil {

    fun formatNum(num: String, b: Boolean): StringBuffer {
        val sb = StringBuffer()
        val b0 = BigDecimal("100")
        val b1 = BigDecimal("10000")
        val b2 = BigDecimal("100000000")
        val b3 = BigDecimal(num)

        var formatNumStr = ""
        var unit = ""

        // 以百为单位处理
        if (b) {
            return if (b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1) {
                sb.append("99+")
            } else sb.append(num)
        }

        // 以万为单位处理
        if (b3.compareTo(b1) == -1) {
            formatNumStr = b3.toString()
        } else if (b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1 || b3.compareTo(b2) == -1) {
            unit = "万"

            formatNumStr = b3.divide(b1).toString()
        } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
            unit = "亿"
            formatNumStr = b3.divide(b2).toString()

        }
        if ("" != formatNumStr) {
            var i = formatNumStr.indexOf(".")
            if (i == -1) {
                sb.append(formatNumStr).append(unit)
            } else {
                i++
                val v = formatNumStr.substring(i, i + 1)
                if (v != "0") {
                    sb.append(formatNumStr.substring(0, i + 1)).append(unit)
                } else {
                    sb.append(formatNumStr.substring(0, i - 1)).append(unit)
                }
            }
        }
        return if (sb.isEmpty()) sb.append("0") else sb
    }

    /**
     * 获取资源文件中定义的字符串。
     *
     * @param resId
     * 字符串资源id
     * @return 字符串资源id对应的字符串内容。
     */
    fun getString(resId: Int): String {
        return AppContext.context.resources.getString(resId)
    }

}