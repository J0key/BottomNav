package com.example.uts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uts.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val TAG = "MainActivityLifecycle"
    private lateinit var binding: ActivityLoginBinding

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            loginBtn.setOnClickListener {
                val username = editusername.text.toString().trim()
                val password = passwordEdit.text.toString().trim()
                if (username.equals("Shyra") && password.equals("shyr3104")) {
                    val intentLogin = Intent(this@LoginActivity, HomeActivity::class.java)
                    intentLogin.putExtra(EXTRA_USERNAME, username)
                    startActivity(intentLogin)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Password/Username Salah", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}


