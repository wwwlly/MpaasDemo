package com.mpaas.demo.utils

import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread

object OkhttpUtils {

    const val TAG = "OkhttpUtils"

    //get同步请求
    fun testThread() {
        thread(start = true, name = "test") {
            Logger.d(TAG, "test thread run!")
        }
    }

    //同步请求
    private fun httpSync(request: Request): String? {
        val client = OkHttpClient()
        val call = client.newCall(request)
        val response = call.execute()
        return response.body?.string()
    }

    //异步请求
    private fun httpAsync(request: Request, callback: Callback) {
        val client = OkHttpClient()
        val call = client.newCall(request)
        call.enqueue(callback)
    }

    //get同步请求
    fun getSync(url: String = "https://www.httpbin.org/get?a=1&b=2"): String? = httpSync(
        Request.Builder()
            .url(url)
            .build()
    )

    //get异步请求
    fun getAsync(
        url: String = "https://www.httpbin.org/get?a=1&b=2",
        callback: Callback = object : Callback {
            //请求失败调用
            override fun onFailure(call: Call, e: IOException) {
                Logger.d(TAG, "exception: ${e.message}")
            }

            //请求结束调用（意味着与服务器的通信成功）
            override fun onResponse(call: Call, response: Response) {
                Logger.d(TAG, response.message)
                if (response.isSuccessful) {
                    response.body?.let { Logger.d(TAG, it.string()) }
                }
            }
        }
    ) = httpAsync(
        Request.Builder()
            .url(url)
            .build(),
        callback
    )

    //post同步请求
    fun postSync(
        url: String = "https://www.httpbin.org/post",
        requestBody: RequestBody = FormBody.Builder().add("a", "1").add("b", "2").build()
    ): String? = httpSync(
        Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
    )

    //post异步请求
    fun postAsync(
        url: String = "https://www.httpbin.org/post",
        requestBody: RequestBody = FormBody.Builder().add("a", "1").add("b", "2").build(),
        callback: Callback = object : Callback {
            //请求失败调用
            override fun onFailure(call: Call, e: IOException) {
                Logger.d(TAG, "exception: ${e.message}")
            }

            //请求结束调用（意味着与服务器的通信成功）
            override fun onResponse(call: Call, response: Response) {
                Logger.d(TAG, response.message)
                if (response.isSuccessful) {
                    response.body?.let { Logger.d(TAG, it.string()) }
                }
            }
        }
    ) = httpAsync(
        Request.Builder()
            .url(url)
            .post(requestBody)
            .build(),
        callback
    )
}