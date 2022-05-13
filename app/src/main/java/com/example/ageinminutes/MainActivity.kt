package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var FinalAgeInMins:TextView?=null
    private var TextViewSelectedDate:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val BdayButton:Button=findViewById(R.id.BirthdayButton)
        TextViewSelectedDate=findViewById(R.id.FinalAgeInMins)
        FinalAgeInMins=findViewById(R.id.FinalAgeInMins)
        BdayButton.setOnClickListener{
            BdayButtonClicked()

        }


    }
     private fun BdayButtonClicked(){
        val MyCalendar=Calendar.getInstance()
        val Year=MyCalendar.get(Calendar.YEAR)
        val month=MyCalendar.get(Calendar.MONTH)
        val day=MyCalendar.get(Calendar.DAY_OF_WEEK)

        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,SelectedYear,SelectedMonth,SelectedDayOfMonth ->
            Toast.makeText(this,"The Selected Date is $SelectedDayOfMonth.${SelectedMonth+1}.$SelectedYear",Toast.LENGTH_SHORT).show()
            val SelectedDate="$SelectedDayOfMonth.${SelectedMonth+1}.$SelectedYear"
            TextViewSelectedDate?.text=SelectedDate
            val LocalDateFormat=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val theDate=LocalDateFormat.parse(SelectedDate)

            val selectedDateInMins=theDate.time/60000

            val CurrentDateInMins=LocalDateFormat.parse(LocalDateFormat.format(System.currentTimeMillis()))
            val CurrentTimeInMins=CurrentDateInMins.time/60000
            val diff=CurrentTimeInMins-selectedDateInMins

            FinalAgeInMins?.text=diff.toString()

        },Year,month,day)
         dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
         dpd.show()

    }
}