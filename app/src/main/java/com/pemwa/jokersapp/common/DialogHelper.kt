package com.pemwa.jokersapp.common

import android.content.Context
import android.support.v7.app.AlertDialog
import com.pemwa.jokersapp.R

/**
 * Created by petermwash on 15/05/19.
 */

fun showGeneralError(context: Context) {
    AlertDialog.Builder(context)
        .setTitle(context.getString(R.string.error_title))
        .setMessage(context.getString(R.string.error_message))
        .setPositiveButton(context.getString(R.string.general_positive_button)) { dialog, _ -> dialog.dismiss() }
        .show()
}
