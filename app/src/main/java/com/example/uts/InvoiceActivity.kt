package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts.databinding.ActivityInvoiceBinding

class InvoiceActivity : AppCompatActivity() {
    private lateinit var binidng:  ActivityInvoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cinema =intent.getStringExtra("selectedCinema")
        val seat = intent.getStringExtra("selecetedSeat")
        val payment = intent.getStringExtra("mobile")
        val amount = intent.getStringExtra("pay")
        val formattedDate = intent.getStringExtra("formattedDate")


        with (binding){
            bioskopInv.text = cinema
            paymentInv.text = payment
            seatInv.text = seat
            invDate.text = formattedDate
            price.text = amount

            backBtn.setOnClickListener{
                val intentBack = Intent(this@InvoiceActivity, BuyingActivity::class.java)
                startActivity(intentBack)
            }


        }




    }
}