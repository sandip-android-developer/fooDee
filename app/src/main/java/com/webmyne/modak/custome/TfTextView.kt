package com.webmyne.modak.custome

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

import com.webmyne.modak.R
import com.webmyne.modak.helper.Functions


class TfTextView : AppCompatTextView {

    private var _ctx: Context? = null
    private var isBold: Boolean = false

    constructor(context: Context) : super(context) {
        if (!isInEditMode) {
            this._ctx = context
            init()
        }
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        if (!isInEditMode) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TfTextView, 0, 0)
            try {
                isBold = a.getBoolean(R.styleable.TfTextView_isBold, false)
            } finally {
                a.recycle()
            }

            this._ctx = context
            init()
        }
    }

    fun setBold(isBold: Boolean) {
        this.isBold = isBold
        if (isBold) {
            typeface = _ctx?.let { Functions.getBoldFont(it) }
        } else {
            typeface = _ctx?.let { Functions.getRegularFont(it) }
        }
    }

    private fun init() {
        try {
            // setTypeface(Functions.getLatoFont(_ctx));
            if (isBold) {
                typeface = _ctx?.let { Functions.getBoldFont(it) }
            } else {
                typeface = _ctx?.let { Functions.getRegularFont(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
