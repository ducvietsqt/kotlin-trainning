package com.example.projectstructure

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.projectstructure.databinding.ActivityMainBinding

open class BaseActivity: AppCompatActivity() {
    var loginDialog:Dialog? = null
    override fun onResume() {
        super.onResume()
    }
    fun includeLoading() {

    }
    fun showLoading(isShow: Boolean) {
        if (isShow) {
            if (loginDialog == null) {
                var view = LayoutInflater.from(this).inflate(R.layout.loader, null)
                loginDialog = Dialog(this)
                loginDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                loginDialog?.setContentView(view)
                loginDialog?.setCancelable(false) // false => disabled back

            }
            loginDialog?.let {
                if (!it.isShowing) {
                    it.show()
                }
            }

        } else {
            loginDialog?.let {
                it.hide()
                loginDialog = null
            }
        }
    }

}