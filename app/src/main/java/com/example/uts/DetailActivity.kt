package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            buyBtn.setOnClickListener{
                val btnBuy = Intent(this@DetailActivity, BuyingActivity::class.java)
                startActivity(btnBuy)
            }
            back.setOnClickListener{
                val intentBack = Intent(this@DetailActivity, HomeActivity::class.java)
                startActivity(intentBack)
            }
        }



    }
}