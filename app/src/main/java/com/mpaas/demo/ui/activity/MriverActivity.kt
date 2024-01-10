package com.mpaas.demo.ui.activity

import android.os.Bundle
import android.view.View
import com.mpaas.demo.R
import com.mpaas.mriver.api.integration.Mriver

/**
 * 小程序
 */
class MriverActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mriver)
    }

    fun onClickStart(view: View) {
        Mriver.startApp("0402000000000001")
    }

    fun onClickDebug(view: View) {}
}