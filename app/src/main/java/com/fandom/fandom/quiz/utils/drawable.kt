package com.fandom.fandom.quiz.utils

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.fandom.fandom.quiz.R

fun Context.avatars() = listOf(AppCompatResources.getDrawable(this,R.drawable.avatar01), AppCompatResources.getDrawable(this,R.drawable.avatar02), AppCompatResources.getDrawable(this,R.drawable.avatar03), AppCompatResources.getDrawable(this,R.drawable.avatar04))