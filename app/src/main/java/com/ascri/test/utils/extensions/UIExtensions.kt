package com.ascri.test.utils.extensions

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt


val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()
val Int.pxToDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).roundToInt()

val Float.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()
val Float.dpToPxFloat: Float
    get() = (this * Resources.getSystem().displayMetrics.density)
val Int.dpToPxFloat: Float
    get() = (this * Resources.getSystem().displayMetrics.density)
val Int.pxToDpFloat: Float
    get() = (this / Resources.getSystem().displayMetrics.density)

fun Float.spToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        context.resources.displayMetrics
    ).roundToInt()
}