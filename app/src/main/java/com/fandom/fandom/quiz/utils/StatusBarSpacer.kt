package com.fandom.fandom.quiz.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StatusBarSpacer @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs), KoinComponent {

    private val statusBarHeightManager: StatusBarHeightUtil by inject()

    init {  }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) =
        setMeasuredDimension(widthMeasureSpec, statusBarHeightManager.statusBarHeight)

}
