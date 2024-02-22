package com.mpaas.demo.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alipay.mobile.base.config.ConfigService
import com.mpaas.configservice.adapter.api.MPConfigService
import com.mpaas.demo.R
import com.mpaas.demo.utils.Logger

class ConfigServiceActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ConfigService"
    }

    private var editText: EditText? = null

    private var configKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_service)

        editText = findViewById(R.id.editText)
        editText?.setText("config_key1")
        configKey = editText?.text?.toString() ?: ""

        MPConfigService.loadConfig()
    }

    private val configChangeListener by lazy {
        object : ConfigService.ConfigChangeListener {
            override fun getKeys(): MutableList<String> {
                return mutableListOf(configKey)
            }

            override fun onConfigChange(p0: String?, p1: String?) {
                Logger.d(TAG, "p0: $p0, p1: $p1")
                Toast.makeText(this@ConfigServiceActivity, "p0: $p0, p1: $p1", Toast.LENGTH_LONG)
                    .show()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        MPConfigService.addConfigChangeListener(configChangeListener)
    }

    override fun onPause() {
        super.onPause()
        MPConfigService.removeConfigChangeListener(configChangeListener)
    }

    fun onClickDebug(view: View) {

    }

    fun onClickLoadImmediately(view: View) {
        MPConfigService.loadConfigImmediately(0)
    }

    fun onClickGetConfig(view: View) {
        configKey = editText?.text?.toString() ?: ""
        val configValue = MPConfigService.getConfig(configKey)
        Toast.makeText(this, "config value: $configValue", Toast.LENGTH_LONG).show()
    }
}