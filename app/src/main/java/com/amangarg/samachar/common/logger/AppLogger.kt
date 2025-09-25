package com.amangarg.samachar.common.logger

import android.util.Log

class AppLogger : Logger {
    override fun log(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}