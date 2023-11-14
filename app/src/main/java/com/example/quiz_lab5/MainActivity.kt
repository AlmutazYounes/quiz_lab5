package com.example.quiz_lab5

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var checkBox4: CheckBox
    private lateinit var submitButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup)
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        submitButton = findViewById(R.id.submitButton)
        resetButton = findViewById(R.id.resetButton)

        submitButton.setOnClickListener {
            val isRadioCorrect = checkFirstQuestion()
            val isCheckBoxCorrect = checkSecondQuestion()

            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val currentDate = dateFormat.format(Date())

            var totalScore = if (isRadioCorrect) 50 else 0
            if (isCheckBoxCorrect) totalScore += 50

            val message = "Congratulations! You submitted on $currentDate, you achieved $totalScore%"

            showResultDialog(message)
        }

        resetButton.setOnClickListener {
            radioGroup.clearCheck()
            checkBox1.isChecked = false
            checkBox2.isChecked = false
            checkBox3.isChecked = false
            checkBox4.isChecked = false
        }
    }

    private fun checkFirstQuestion(): Boolean {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        val correctRadioButtonId = R.id.option1 // Replace with the correct answer ID
        return selectedRadioButtonId == correctRadioButtonId
    }

    private fun checkSecondQuestion(): Boolean {
        val correctCheckBox1 = R.id.checkBox1 // Replace with the correct answer ID
        val correctCheckBox2 = R.id.checkBox3 // Replace with the correct answer ID
        return checkBox1.isChecked && checkBox2.isChecked && !checkBox3.isChecked && checkBox4.isChecked
    }

    private fun showResultDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Quiz Result")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
