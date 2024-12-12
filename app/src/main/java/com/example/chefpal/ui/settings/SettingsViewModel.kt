package com.example.chefpal.ui.settings

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModel
import com.example.chefpal.foodLimitationsText


private var _textWatcher: TextWatcher = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // this function is called before text is edited
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        foodLimitationsText = s.toString()
    }

    override fun afterTextChanged(s: Editable) {
        // this function is called after text is edited
    }
}

class SettingsViewModel : ViewModel() {
    val textWatcher = _textWatcher
}