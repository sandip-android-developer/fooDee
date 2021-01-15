package com.webmyne.modak.custome

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import com.webmyne.modak.helper.Functions

class TfButton : AppCompatButton {

    private var _ctx: Context? = null

    constructor(context: Context) : super(context) {
        if (!isInEditMode) {
            this._ctx = context
            init()
        }
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        if (!isInEditMode) {
            this._ctx = context
            init()
        }
    }

    private fun init() {
        try {
            typeface = Functions.getBoldFont(_ctx!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
