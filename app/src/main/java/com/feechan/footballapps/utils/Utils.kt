package com.feechan.footballapps.utils

import android.content.Context
import android.view.View
import com.feechan.footballapps.data.sqllite.SportOpenHelper
import java.text.SimpleDateFormat
import java.util.*

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

val Context.database: SportOpenHelper
    get() = SportOpenHelper.getInstance(applicationContext)

fun toSimpleString(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}