package com.lumston.finvivirchallenge.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lumston.finvivirchallenge.databinding.ViewProgressIndicatorBinding

class ProgressIndicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr)  {
    init {
        ViewProgressIndicatorBinding.inflate(LayoutInflater.from(context), this)
    }
}