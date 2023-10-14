package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(LoginActivity.EXTRA_USERNAME)


        with(binding) {
            username.text = name

            shigatsuTxt.setOnClickListener {
                val shigatsuimageIntent = Intent(this@HomeActivity, DetailActivity::class.java)
                startActivity(shigatsuimageIntent)
            }
            shigatsu.setOnClickListener{
                val shigatsuIntent = Intent(this@HomeActivity, DetailActivity::class.java)
                startActivity(shigatsuIntent)
            }

        }
    }
}