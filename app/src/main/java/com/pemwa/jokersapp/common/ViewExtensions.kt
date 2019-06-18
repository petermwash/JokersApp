package com.pemwa.jokersapp.common

import android.support.v4.view.ViewPager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

/**
 * Created by petermwash on 15/05/19.
 */



// I am using the inline function to avoid creation of multiple unnecessary Function objects.
//It enables copying of the the function at the call site

//The use of "crossinline is to help us enforce the control flow (return statement) of the
// lambda functions passed as arguments in inline functions.
//Therefore avoiding non-local control flow

inline fun View.onClick(crossinline onClickHandler: () -> Unit) {
    setOnClickListener { onClickHandler() }
}

inline fun EditText.onTextChanged(crossinline onTextChangeHandler: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChangeHandler(input?.toString() ?: "")
        }
    })
}

inline fun ViewPager.onPageChange(crossinline onPageChangeHandler: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) = Unit
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

        override fun onPageSelected(position: Int) = onPageChangeHandler(position)
    })
}