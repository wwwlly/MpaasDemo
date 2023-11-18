package com.mpaas.demo.service

import android.content.Context
import com.alipay.mobile.common.rpc.RpcException
import com.alipay.pushsdk.content.MPPushMsgServiceAdapter
import com.mpaas.demo.MainApplication
import com.mpaas.mas.adapter.api.MPLogger
import com.mpaas.mps.adapter.api.MPPush

class MyPushMsgService : MPPushMsgServiceAdapter() {

    companion object {
        const val TAG = "MyPushMsgService"

        fun bindUser(context: Context, token: String?) {
            try {
//                val bindResult = MPPush.bind(context, MainApplication.USER_ID, token)
                val bindResult = MPPush.bind(context, token)
                if (bindResult.success) {
                    MPLogger.debug(TAG, "绑定userId 成功")
                } else {
                    MPLogger.debug(TAG, "绑定userId 失败：${bindResult.code}")
                }
            } catch (exception: RpcException) {
                MPLogger.debug(TAG, "MPPush.bind error: ${exception.msg}")
            }
        }

        fun unBindUser(context: Context, token: String?) {
            try {
                val bindResult = MPPush.unbind(context, MainApplication.USER_ID, token)
                if (bindResult.success) {
                    MPLogger.debug(TAG, "解绑userId 成功")
                } else {
                    MPLogger.debug(TAG, "解绑userId 失败：${bindResult.code}")
                }
            } catch (exception: RpcException) {

            }
        }
    }

    override fun onTokenReceive(token: String?) {
        super.onTokenReceive(token)
        MPLogger.debug(TAG, "收到自建通道 token: $token")

        if (applicationContext != null) {
            MPLogger.debug(TAG, "context: ${applicationContext.javaClass.simpleName}")
        } else {
            MPLogger.debug(TAG, "context is null")
        }
        // 收到 token 后，绑定 userId
        // 开发者可缓存 token，在获取到自己的 userId 后再绑定
        bindUser(applicationContext, token)

    }
}