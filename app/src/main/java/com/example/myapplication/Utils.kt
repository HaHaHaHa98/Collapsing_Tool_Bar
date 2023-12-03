package com.example.myapplication

import android.content.Context
import android.util.TypedValue.*

fun Context.toPx(dp: Int): Float = applyDimension(
    COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics)