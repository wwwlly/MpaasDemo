package com.mpaas.demo.utils

import android.app.Activity
import android.widget.Toast

object ToastUtils {

    fun Activity.showToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}