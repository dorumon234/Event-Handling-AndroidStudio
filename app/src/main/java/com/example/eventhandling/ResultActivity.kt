package com.example.eventhandling;

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eventhandling.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult: TextView = findViewById(R.id.tvResult)

        val name = intent.getStringExtra("name") ?: ""
        val nis = intent.getStringExtra("nis") ?: ""
        val studentClass = intent.getStringExtra("class") ?: ""
        val birthDate = intent.getStringExtra("birthDate") ?: ""
        val gender = intent.getStringExtra("gender") ?: ""
        val extracurriculars = intent.getStringArrayListExtra("extracurriculars") ?: arrayListOf()
        val schedule = intent.getStringExtra("schedule") ?: ""

        val result = buildString {
            append("Nama: $name\n\n")
            append("NIS: $nis\n\n")
            append("Kelas: $studentClass\n\n")
            append("Tanggal Lahir: $birthDate\n\n")
            append("Jenis Kelamin: $gender\n\n")
            append("Ekstrakurikuler:\n")

            extracurriculars.forEach { item ->
                append("- $item\n")
            }

            append("\nJadwal Kegiatan: $schedule")
        }

        tvResult.text = result
    }
}