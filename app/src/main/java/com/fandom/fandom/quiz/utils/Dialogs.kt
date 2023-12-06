package com.fandom.fandom.quiz.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.fandom.fandom.quiz.R


fun Activity.showQuestionDialog(
    question: String,
    firstButtonText: String= getString(R.string.accept),
    secondButtonText: String = getString(R.string.cancel),
    firstButtonAction: () -> Unit = {},
    secondButtonAction: () -> Unit = {}) {
    AlertDialog.Builder(this)
        .setMessage(question)
        .setPositiveButton(firstButtonText) { d, _ ->
            firstButtonAction()
            d.dismiss()
        }.setNegativeButton(secondButtonText) { d, _ ->
            secondButtonAction()
            d.dismiss()
        }.setCancelable(false)
        .show()
}
fun Fragment.showQuestionDialog(
    question: String,
    firstButtonText: String= getString(R.string.accept),
    secondButtonText: String = getString(R.string.cancel),
    firstButtonAction: () -> Unit = {},
    secondButtonAction: () -> Unit = {}) {
    AlertDialog.Builder(requireContext())
        .setMessage(question)
        .setPositiveButton(firstButtonText) { d, _ ->
            firstButtonAction()
            d.dismiss()
        }.setNegativeButton(secondButtonText) { d, _ ->
            secondButtonAction()
            d.dismiss()
        }.setCancelable(false)
        .show()
}

fun Fragment.showSimpleDialog(text: String, action: () -> Unit = {}) {
    AlertDialog.Builder(requireContext())
        .setMessage(text)
        .setPositiveButton(android.R.string.ok) { d, _ ->
            action()
            d.dismiss()
        }
        .setCancelable(false)
        .show()
}
