package com.example.eventhandling

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    // View references
    private lateinit var etName: EditText
    private lateinit var etNIS: EditText
    private lateinit var spinnerClass: Spinner
    private lateinit var btnDatePicker: Button
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var cbFootball: CheckBox
    private lateinit var cbBasketball: CheckBox
    private lateinit var cbMusic: CheckBox
    private lateinit var cbDebate: CheckBox
    private lateinit var autoCompleteSchedule: AutoCompleteTextView
    private lateinit var btnSubmit: Button

    // Data variables
    private var selectedDate = ""
    private var selectedClass = ""
    private var selectedGender = ""
    private val selectedExtracurriculars = mutableListOf<String>()
    private var selectedSchedule = ""

    // Schedule suggestions
    private val scheduleSuggestions = arrayOf(
        "Senin 07:00-09:00",
        "Senin 13:00-15:00",
        "Selasa 08:00-10:00",
        "Selasa 14:00-16:00",
        "Rabu 09:00-11:00",
        "Rabu 15:00-17:00",
        "Kamis 10:00-12:00",
        "Jumat 07:30-09:30"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize all views
        initViews()

        // Setup components
        setupSpinner()
        setupAutoComplete()
        setupListeners()
    }

    private fun initViews() {
        etName = findViewById(R.id.etName)
        etNIS = findViewById(R.id.etNIS)
        spinnerClass = findViewById(R.id.spinnerClass)
        btnDatePicker = findViewById(R.id.btnDatePicker)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        cbFootball = findViewById(R.id.cbFootball)
        cbBasketball = findViewById(R.id.cbBasketball)
        cbMusic = findViewById(R.id.cbMusic)
        cbDebate = findViewById(R.id.cbDebate)
        autoCompleteSchedule = findViewById(R.id.autoCompleteSchedule)
        btnSubmit = findViewById(R.id.btnSubmit)
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.class_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerClass.adapter = adapter
        }
    }

    private fun setupAutoComplete() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            scheduleSuggestions
        )
        autoCompleteSchedule.setAdapter(adapter)

        autoCompleteSchedule.setOnItemClickListener { _, _, position, _ ->
            selectedSchedule = scheduleSuggestions[position]
        }
    }

    private fun setupListeners() {
        // Date Picker
        btnDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

        // Gender Selection
        radioGroupGender.setOnCheckedChangeListener { _, checkedId ->
            selectedGender = when (checkedId) {
                R.id.radioMale -> "Laki-laki"
                R.id.radioFemale -> "Perempuan"
                else -> ""
            }
        }

        // Extracurricular Checkboxes
        val checkboxListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val item = buttonView.text.toString()
            if (isChecked) {
                selectedExtracurriculars.add(item)
            } else {
                selectedExtracurriculars.remove(item)
            }
        }
        cbFootball.setOnCheckedChangeListener(checkboxListener)
        cbBasketball.setOnCheckedChangeListener(checkboxListener)
        cbMusic.setOnCheckedChangeListener(checkboxListener)
        cbDebate.setOnCheckedChangeListener(checkboxListener)

        // Submit Button
        btnSubmit.setOnClickListener {
            if (validateInput()) {
                saveDataAndShowResult()
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDate = "$dayOfMonth/${month + 1}/$year"
                btnDatePicker.text = selectedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun validateInput(): Boolean {
        var isValid = true

        if (etName.text.toString().trim().isEmpty()) {
            etName.error = "Nama harus diisi"
            isValid = false
        }

        if (etNIS.text.toString().trim().isEmpty()) {
            etNIS.error = "NIS harus diisi"
            isValid = false
        }

        if (selectedDate.isEmpty()) {
            Toast.makeText(this, "Pilih tanggal lahir", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (selectedGender.isEmpty()) {
            Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (selectedExtracurriculars.isEmpty()) {
            Toast.makeText(this, "Pilih minimal satu ekstrakurikuler", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (autoCompleteSchedule.text.toString().trim().isEmpty()) {
            autoCompleteSchedule.error = "Pilih hari dan jam kegiatan"
            isValid = false
        } else {
            selectedSchedule = autoCompleteSchedule.text.toString().trim()
        }

        return isValid
    }

    private fun saveDataAndShowResult() {
        selectedClass = spinnerClass.selectedItem.toString()

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("name", etName.text.toString().trim())
            putExtra("nis", etNIS.text.toString().trim())
            putExtra("class", selectedClass)
            putExtra("birthDate", selectedDate)
            putExtra("gender", selectedGender)
            putStringArrayListExtra("extracurriculars", ArrayList(selectedExtracurriculars))
            putExtra("schedule", selectedSchedule)
        }

        startActivity(intent)
    }
}