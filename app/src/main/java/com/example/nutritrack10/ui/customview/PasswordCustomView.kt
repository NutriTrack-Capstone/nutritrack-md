package com.example.nutritrack10.ui.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText


class PasswordCustomView(context: Context, attrs: AttributeSet) :
    AppCompatEditText(context, attrs) {
    init {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassword(s?.toString())
            }
        })
    }

    private fun validatePassword(password: String?) {
        if (password != null && password.length < 8) {
            error = "Password must be 8 character minimum"
        } else {
            error = null
        }
    }
}