package com.example.uts

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import com.example.uts.databinding.ActivityBuyingBinding
import java.util.Locale

class BuyingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyingBinding
    private lateinit var dateSpin: TextView
    private lateinit var timeSpin: TextView

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBuyingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateSpin = binding.dateSpinner
        timeSpin = binding.timeSpinner

//        dateSpin=findViewById(R.id.date_spinner)
//        timeSpin=findViewById(R.id.time_spinner)
        val cinema = resources.getStringArray(R.array.cinema)
        val seat = resources.getStringArray(R.array.seat)
        val payment = resources.getStringArray(R.array.payment)
        val bank = resources.getStringArray(R.array.mobile)
//        val amount = resources.getStringArray(R.array.amount)


        with(binding) {
            val myCalendar = Calendar.getInstance()

            val datePicker= DatePickerDialog.OnDateSetListener{
                    view,year,month,dayOfMonth->
                myCalendar.set(Calendar.YEAR,year)
                myCalendar.set(Calendar.MONTH,month)
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateLable(myCalendar)
            }
            val timePickerDialog = TimePickerDialog(this@BuyingActivity, TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                myCalendar.set(Calendar.MINUTE, minute)
                updateTimeLabel(myCalendar)
            }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), false)

            dateSpinner.setOnClickListener{
                val datePickerDialog=DatePickerDialog(this@BuyingActivity,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.show()
            }
            timeSpinner.setOnClickListener {
                timePickerDialog.show()
            }


//            Adapter
            val cinemaAdapter = ArrayAdapter(this@BuyingActivity, R.layout.spinner_color, cinema)
            cinemaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cinemaSpinner.adapter = cinemaAdapter

            val paymentAdapter = ArrayAdapter(this@BuyingActivity, R.layout.spinner_color , payment)
            paymentAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
            paymentSpinner.adapter = paymentAdapter

            val seatAdapter = ArrayAdapter(this@BuyingActivity, R.layout.spinner_color , seat)
            seatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            seatEdittext.adapter = seatAdapter

            val bankAdapter = ArrayAdapter(this@BuyingActivity, R.layout.spinner_color, bank)
            bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bankSpinner.adapter = bankAdapter


            amountEdittext.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // This method is called before the text changes, if you need to perform any actions.
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // This method is called as the user types.
                    // It's a good place to validate the input and update the price based on valid input.
                    val inputText = s.toString()

                    try {
                        val inputValue = inputText.toInt()
                        if (inputValue >= 0) {
                            val currentPrice = inputValue * 35000
                            price.text = "Rp     $currentPrice" // You can format it as needed
                        } else {
                            price.text = "Invalid Input"
                        }
                    } catch (e: NumberFormatException) {
                        price.text = "Invalid Input"
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    // This method is called after the text changes, if you need to perform any actions.
                }
            })



            buy.setOnClickListener {
                val selectedCinema = cinemaSpinner.selectedItem.toString()
                val selectedPayment = paymentSpinner.selectedItem.toString()
                val selectedSeat = seatEdittext.selectedItem.toString()
                val datePicked = dateSpin.text.toString()
                val timePicked = timeSpin.text.toString()
                val bank = bankSpinner.selectedItem.toString()


                val intent = Intent(this@BuyingActivity, InvoiceActivity::class.java)
                intent.putExtra("selectedCinema", selectedCinema)
                val mobile = "$bank $selectedPayment"
                val formattedDate = "$datePicked, $timePicked"
                intent.putExtra("mobile", mobile)
                intent.putExtra("formattedDate", formattedDate)
                intent.putExtra("selecetedSeat", selectedSeat)
                intent.putExtra("pay",price.text)
                startActivity(intent)
            }
            backBtn.setOnClickListener{
                val intentnBack = Intent(this@BuyingActivity, DetailActivity::class.java)
                startActivity(intentnBack)

                val intentBuy = Intent(this@BuyingActivity, InvoiceActivity::class.java)
                startActivity(intentBuy)
            }
        }
//
//

        }
    private fun updateLable(myCalendar: Calendar){
        val myFormat = "dd-MM-yyyy"
        val sdf= SimpleDateFormat(myFormat, Locale.UK)
        dateSpin.setText(sdf.format(myCalendar.time))
    }
    private fun updateTimeLabel(myCalendar: Calendar){
        val myFormat = "HH:mm"
        val sdf= SimpleDateFormat(myFormat, Locale.UK)
        timeSpin.setText(sdf.format(myCalendar.time))
    }
}