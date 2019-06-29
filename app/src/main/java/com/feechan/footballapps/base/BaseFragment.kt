package com.feechan.footballapps.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

abstract class BaseFragment : Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setup()
    }

    abstract fun setup()

    fun showLoadingBar(){
        if( activity is BaseActivity){
            (activity as BaseActivity).showLoadingBar()
        } else {

        }
    }

    fun hideLoadingBar(){
        if( activity is BaseActivity){
            (activity as BaseActivity).hideLoadingBar()
        } else {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
    }

//    fun showFailedErrorMessage(errorMessage: String) {
//        //DialogUtils.showErrorMessageDialog(activity, errorMessage)
//    }
//
//    fun showNetworkErrorMessage() {
//        //showFailedErrorMessage()
//    }
}