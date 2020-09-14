package com.ardayuksel.androidroomapp.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ardayuksel.androidroomapp.R
import com.ardayuksel.androidroomapp.data.viewmodels.RegisterViewModel
import com.ardayuksel.androidroomapp.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        supportActionBar?.hide()

        var registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        btnRegister.setOnClickListener {
            var username = etRegisterName.text.toString()
            var password = etRegisterPassword.text.toString()
            if (username.isNotEmpty() || password.isNotEmpty()) {
                registerViewModel.saveToDataStore(username, password)
                ToastUtil.makeToast(this, "User $username has been created")
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                ToastUtil.makeToast(this, "Please enter both values")
            }
        }
    }
}