
package com.webmyne.modak.helper

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    val all: Map<String, *>
        get() = preferences.all

    init {
        preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    fun save(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun RemoveAll() {
        editor.clear()
    }


    fun save(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun save(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun save(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    fun save(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    fun save(key: String, value: Set<String>) {
        editor.putStringSet(key, value).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    fun getString(key: String, defValue: String): String? {
        return preferences.getString(key, defValue)
    }

    fun getInt(key: String, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }

    fun getFloat(key: String, defValue: Float): Float {
        return preferences.getFloat(key, defValue)
    }

    fun getLong(key: String, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }

    fun getStringSet(key: String, defValue: Set<String>): Set<String>? {
        return preferences.getStringSet(key, defValue)
    }

    fun remove(key: String) {
        editor.remove(key).apply()
    }

    private class Builder(context: Context?) {

        private val context: Context

        init {
            if (context == null) {
                throw IllegalArgumentException("Context must not be null.")
            }
            this.context = context.applicationContext
        }

        /**
         * Method that creates an instance of Prefs
         *
         * @return an instance of Prefs
         */
        fun build(): Prefs {
            return Prefs(context)
        }
    }

    companion object {

        private val TAG = "Base_ws"

        internal var singleton: Prefs? = null

        internal lateinit var preferences: SharedPreferences

        internal lateinit var editor: SharedPreferences.Editor

        fun with(context: Context): Prefs {
            if (singleton == null) {
                singleton = Builder(context).build()
            }
            return singleton as Prefs
        }
    }
}
