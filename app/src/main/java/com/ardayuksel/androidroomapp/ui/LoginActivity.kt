package com.ardayuksel.androidroomapp.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ardayuksel.androidroomapp.R
import com.ardayuksel.androidroomapp.data.viewmodels.LoginViewModel
import com.ardayuksel.androidroomapp.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        supportActionBar?.hide()

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        supportActionBar?.title = "Log In"

        btnLogin?.setOnClickListener {
            val username = etLoginName.text.toString()
            val password = etLoginPassword.text.toString()
            viewModel.readUsernameFromDataStore.observe(this, { user ->
                if (user == username) {
                    viewModel.readPasswordFromDataStore.observe(this, { pass ->
                        if (pass == password) {
                            startActivity(Intent(this, ListActivity::class.java))
                            finish()
                        } else {
                            ToastUtil.makeToast(this, "Invalid username or password")
                        }
                    })
                } else {
                    ToastUtil.makeToast(this, "Invalid username or password")
                }
            })
        }

        btnRegister?.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}