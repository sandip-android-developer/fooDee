package com.webmyne.modak.custome

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import com.webmyne.modak.helper.Functions

class TfEditText : AppCompatEditText {

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
            //            setFloatingLabel(FLOATING_LABEL_HIGHLIGHT);
            //            setHintTextColor(ContextCompat.getColor(_ctx, R.color.yellow));
            //            setPrimaryColor(ContextCompat.getColor(_ctx, R.color.yellow));
            //            setAccentTypeface(Functions.getRegularFont(_ctx));
            typeface = _ctx?.let { Functions.getRegularFont(it) }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}
