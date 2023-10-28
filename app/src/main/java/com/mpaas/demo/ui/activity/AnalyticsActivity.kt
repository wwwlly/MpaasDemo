package com.mpaas.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.alipay.mobile.framework.app.ui.BaseAppCompatActivity
import com.mpaas.demo.R
import com.mpaas.mas.adapter.api.MPLogger
import kotlinx.android.synthetic.main.activity_analytics.*
import java.util.*

/**
 * 移动分析服务（Mobile Analysis Service，简称 MAS）
 */
class AnalyticsActivity : BaseAppCompatActivity() {

    private val userId by lazy {
        MPLogger.getUserId()
    }

    companion object {
        const val TAG = "AnalyticsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        initView()
    }

    private fun initView() {
        user_active_tips.text = resources.getString(R.string.user_active_tips, userId)

        user_active_btn.setOnClickListener {
            testUserActive()
        }

        behavior_log_btn.setOnClickListener {
            testBehaviorLog()
        }

        crash_log_btn.setOnClickListener {
            testCrashLog()
        }

        block_log_btn.setOnClickListener {
            testBlockLog()
        }

        anr_log_btn.setOnClickListener {
            testANRLog()
        }

        automation_log_btn.setOnClickListener {
            testAutomationLog()
        }

        log_report_btn.setOnClickListener {
            testLogReport()
        }
    }

    private fun testUserActive() {
        // 设置 userId 并上报用户登录，白名单功能也将使用这里设置的 userId
        MPLogger.reportUserLogin(userId)
        // 只设置 userId 可调用
//        MPLogger.setUserId(userId);
        // 获取 userId
//        MPLogger.getUserId();
        Toast.makeText(this, getString(R.string.user_active_result), Toast.LENGTH_SHORT).show()
    }

    private fun testBehaviorLog() {
        // logId 描述当前埋点的关键词，控制台自定义分析中事件id对应该字段
        // logId 不能为空
        val logId = "PayResults"

        // bizType 标识业务类型，相同 bizType 的自定义日志将遵循同样的上报策略，存储在同一个文件中，文件名为进程名_bizType
        // bizType 可以为空，默认为 userbehavor
        val bizType = "Pay"

        // params 为自定义参数，控制台自定义分析中事件的自定义属性对应这些键值对
        // params 可以为空
        val params: MutableMap<String, String> = HashMap()
        params["time"] = "2018-07-27"

        // 可调用多个重载方法
//        MPLogger.event(logId);
//        MPLogger.event(logId, bizType);
//        MPLogger.event(logId, params);
        MPLogger.event(logId, bizType, params)
        Toast.makeText(this, getString(R.string.behavior_log_result), Toast.LENGTH_SHORT).show()
    }

    private fun testCrashLog() {
        throw RuntimeException(getString(R.string.mock_crash))
    }

    private fun testAutomationLog() {
//        startActivity(Intent(this, AutomationActivity::class.java))
    }

    private fun testBlockLog() {
        val random = Random()
        val i = random.nextInt(3)
        if (i == 0) {
            makeBlock1()
        } else if (i == 1) {
            makeBlock2()
        } else {
            makeBlock3()
        }
        Toast.makeText(this, getString(R.string.block_log_result), Toast.LENGTH_SHORT).show()
    }

    private fun makeBlock1() {
        MPLogger.info(TAG, "makeBlock1")
        doMakeBlock()
    }

    private fun makeBlock2() {
        MPLogger.info(TAG, "makeBlock2")
        doMakeBlock()
    }

    private fun makeBlock3() {
        MPLogger.info(TAG, "makeBlock3")
        doMakeBlock()
    }

    private fun doMakeBlock() {
        // 模拟一段卡顿，框架自动捕获
        val time = 3 * 1000
        try {
            Thread.sleep(time.toLong())
        } catch (e: InterruptedException) {
            MPLogger.warn(TAG, e)
        }
    }

    private fun testANRLog() {
        // 模拟一段卡死，框架自动捕获
        val time = 6 * 1000
        try {
            Thread.sleep(time.toLong())
        } catch (e: InterruptedException) {
            MPLogger.warn(TAG, e)
        }
        Toast.makeText(this, getString(R.string.anr_log_result), Toast.LENGTH_SHORT).show()
    }

    private fun testLogReport() {
        MPLogger.uploadAll()
        Toast.makeText(this, getString(R.string.report_log_result), Toast.LENGTH_SHORT).show()
    }
}