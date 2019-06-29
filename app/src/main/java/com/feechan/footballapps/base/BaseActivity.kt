package com.feechan.footballapps.base

import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import com.feechan.footballapps.utils.invisible
import com.feechan.footballapps.utils.visible

abstract class BaseActivity : AppCompatActivity() {
    lateinit var progressBar: ProgressBar

    fun showLoadingBar(){
        progressBar.visible()
    }

    fun hideLoadingBar() {
        progressBar.invisible()
    }
}