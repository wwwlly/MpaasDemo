package com.mpaas.demo.ui.activity

import android.os.Bundle
import android.view.View
import com.mpaas.demo.R
import com.mpaas.mps.adapter.api.MPPush

class MsgPushActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)
    }

    fun bindUser(view: View) {
//        val bindResult = MPPush.bind(this, )
    }

    fun unBindUser(view: View) {

    }
}