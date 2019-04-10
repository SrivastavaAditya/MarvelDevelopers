package com.example.adityasrivastava.marvelcharacters.dialogs

import android.app.Dialog
import android.content.Context
import com.example.adityasrivastava.marvelcharacters.R

class LoadingDialog(context: Context) {
    var dialog: Dialog

    init {
        dialog = Dialog(context)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.loading_dialog)
    }

    companion object {
        fun getInstance(context: Context): LoadingDialog {
            return LoadingDialog(context)
        }
    }

    fun show() {
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}