package com.mpaas.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alipay.android.phone.scancode.export.ScanRequest
import com.alipay.android.phone.scancode.export.adapter.MPScan
import com.mpaas.demo.R
import com.mpaas.demo.utils.Logger

/**
 * 扫码
 */
class ScanActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ScanActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

    }

    fun onClickScan(view: View) {
        MPScan.startMPaasScanActivity(this, ScanRequest()) { result, data ->
            if (data == null || data.data == null) return@startMPaasScanActivity

            Logger.d(TAG, "result : $result , data : ${data.data.toString()}")
        }
    }
}